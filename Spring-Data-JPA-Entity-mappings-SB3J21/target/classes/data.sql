--  INSERT INTO ANIMAL (NAME, SPECIES, AGE, HABITAT) VALUES
-- ('Lion', 'Panthera leo', 5, 'Savannah'),
-- 'Elephant', 'Loxodonta africana', 25, 'Grassland'),
-- ('Penguin', 'Aptenodytes forsteri', 10, 'Antarctica'),
-- ('Giraffe', 'Giraffa camelopardalis', 12, 'Savannah'),
-- ('Tiger', 'Panthera tigris', 8, 'Rainforest');



-- Inserting data if it doesn't already exist
INSERT INTO ANIMAL (NAME, SPECIES, AGE, HABITAT)
SELECT 'Lion', 'Panthera leo', 5, 'Savannah'
WHERE NOT EXISTS (
    SELECT 1 FROM ANIMAL WHERE NAME = 'Lion' AND SPECIES = 'Panthera leo'
);

INSERT INTO ANIMAL (NAME, SPECIES, AGE, HABITAT)
SELECT 'Elephant', 'Loxodonta africana', 25, 'Grassland'
WHERE NOT EXISTS (
    SELECT 1 FROM ANIMAL WHERE NAME = 'Elephant' AND SPECIES = 'Loxodonta africana'
);
-- Repeat for other records
