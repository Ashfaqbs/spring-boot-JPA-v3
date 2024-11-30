
### **Flow**
1. **Normal Data (No Booleans Triggered)**  
   - The method saves the product to the database via `productRepository.save(product)`.
   - No exception is triggered, so the transaction completes successfully.
   - **Result**: The data is saved.

2. **Custom Exception Triggered (`triggerCustomException=true`)**  
   - The product is saved initially via `productRepository.save(product)`.
   - After saving, a `CustomException` is thrown.
   - Since `@Transactional` is configured to roll back for `CustomException`, the transaction is rolled back.
   - **Result**: No data is saved (rollback happens).

3. **Null Pointer Exception Triggered (`triggerNullPointer=true`)**  
   - The product is saved initially via `productRepository.save(product)`.
   - After saving, a `NullPointerException` is thrown.
   - Since `@Transactional` is configured to roll back for `NullPointerException`, the transaction is rolled back.
   - **Result**: No data is saved (rollback happens).

4. **IllegalArgumentException Triggered (Name = "illegal")**  
   - The product is saved initially via `productRepository.save(product)`.
   - After saving, an `IllegalArgumentException` is thrown.
   - Since `@Transactional` is configured **not** to roll back for `IllegalArgumentException`, the transaction commits.
   - **Result**: The data is saved.

---

### **Internal Working of Transactions**
Let’s dive into how Spring handles this under the hood:

1. **Proxy Creation**  
   - Spring creates a proxy object for the `ProductService` bean when the `@Transactional` annotation is detected. This proxy is responsible for managing the transaction lifecycle.

2. **Transaction Lifecycle**  
   - When the `saveProductWithCustomRules` method is called, the proxy:
     - Starts a database transaction (using the transaction manager configured for your application).
     - Passes control to the actual method implementation.

3. **Entity Save (`productRepository.save`)**  
   - At this point, the `productRepository.save(product)` is executed.
   - This sends an `INSERT` SQL query to the database.
   - Depending on your JPA implementation, the data might not yet be committed to the database but is staged for commit. This is because the transaction is still open.

4. **Exception Handling**  
   - After `productRepository.save(product)` completes, if an exception is thrown:
     - Spring catches the exception through the transaction proxy.
     - Checks if the exception matches any `rollbackFor` or `noRollbackFor` rules defined in `@Transactional`.
     - If the exception matches `rollbackFor`, it performs a **rollback**, undoing the save operation (staged changes are discarded).
     - If the exception matches `noRollbackFor`, the transaction proceeds to **commit** the changes to the database.

5. **Commit or Rollback**  
   - If no exception is thrown or if the exception is configured for `noRollbackFor`, the transaction is committed.
   - If an exception matches `rollbackFor`, the transaction is rolled back, and changes are not persisted.

---

### **Step-by-Step Internal Mechanism**
For your specific scenario:
1. **Start Transaction**  
   - The transaction begins when the method `saveProductWithCustomRules` is invoked.
   - Any subsequent database operations are staged within this transaction.

2. **Save Operation (`productRepository.save`)**  
   - The save operation executes, and JPA sends an SQL `INSERT` to the database.
   - This operation is part of the current transaction but is not yet committed.

3. **Exception Thrown**  
   - An exception is thrown based on the condition:
     - If `triggerCustomException` is `true`, a `CustomException` is thrown.
     - If `triggerNullPointer` is `true`, a `NullPointerException` is thrown.
     - If the product name is `"illegal"`, an `IllegalArgumentException` is thrown.

4. **Transaction Proxy Handles Exception**  
   - The Spring transaction proxy intercepts the exception and checks the `@Transactional` configuration:
     - For `CustomException` and `NullPointerException`, it triggers a rollback.
     - For `IllegalArgumentException`, it does not roll back and allows the transaction to commit.

5. **Commit or Rollback**  
   - If no rollback is required, all staged changes are committed to the database.
   - If rollback is triggered, all staged changes are discarded, leaving the database in its previous state.

---

### **Why Does Rollback Discard Changes After `save`?**
- JPA operates within the boundaries of a transaction.
- When you call `productRepository.save(product)`:
  - The SQL is executed, but the changes are still part of the open transaction.
  - These changes are not visible outside the transaction until the transaction is committed.
  - If the transaction rolls back, the database discards all changes within that transaction, including the `INSERT`.

---
