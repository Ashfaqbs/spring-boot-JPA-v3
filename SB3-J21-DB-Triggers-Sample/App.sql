working on already created table
--  sequence and triggers

CREATE TABLE public.xstudents_table (
    id BIGINT NOT NULL,
    name VARCHAR(100),
    age INT,
    address VARCHAR(255),
    PRIMARY KEY (id)
);



INSERT INTO public.xstudents_table (id, name, age, address) VALUES
(1, 'John Doe', 25, '123 Main St'),
(2, 'Jane Smith', 22, '456 Oak Ave'),
(3, 'Mike Johnson', 27, '789 Pine Rd'),
(4, 'Anna Lee', 24, '101 Maple St');



SELECT id, "name", age, address
FROM public.xstudents_table;

CREATE SEQUENCE student_id_seq START WITH 5 INCREMENT BY 1;

ALTER TABLE public.xstudents_table ALTER COLUMN id SET DEFAULT nextval('student_id_seq');


INSERT INTO public.xstudents_table (name, age, address) VALUES
('Paul Green', 29, '567 Birch St'),
('Linda White', 30, '890 Cedar Ave');


commit;




- just logs in console or output window
CREATE OR REPLACE FUNCTION public.log_student_insert() 
RETURNS TRIGGER AS $$
BEGIN
    -- Log message on every student insert
    RAISE NOTICE 'New student added: %', NEW.name;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_student_insert
AFTER INSERT ON public.xstudents_table
FOR EACH ROW
EXECUTE FUNCTION log_student_insert();




INSERT INTO public.xstudents_table (name, age, address) VALUES
('Sara Connor', 22, '123 Skynet Ave');


--trigger OP : New student added: Sara Connor


CREATE TABLE public.trigger_log (
    id SERIAL PRIMARY KEY,
    action_type VARCHAR(50),
    triggered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT
);


- func adds data
CREATE OR REPLACE FUNCTION log_trigger_event()
RETURNS TRIGGER AS $$
BEGIN
    -- Insert into the trigger_log table every time this trigger fires
    INSERT INTO trigger_log(action_type, description)
    VALUES ('INSERT', 'Inserted into students table, ID: ' || NEW.id);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER log_insert_trigger
AFTER INSERT ON public.xstudents_table
FOR EACH ROW
EXECUTE FUNCTION log_trigger_event();



INSERT INTO public.xstudents_table (name, age, address) VALUES ('Alice', 22, '123 Main St');


SELECT * FROM public.trigger_log;
op 
1	INSERT	2024-10-02 19:04:18.267	Inserted into students table, ID: 11
2	INSERT	2024-10-02 19:04:54.956	Inserted into students table, ID: 12
3	INSERT	2024-10-02 19:04:55.976	Inserted into students table, ID: 13
4	INSERT	2024-10-02 19:04:57.364	Inserted into students table, ID: 14
5	INSERT	2024-10-02 19:10:38.555	Inserted into students table, ID: 15



output window console :

New student added: Sara Connor
there is no transaction in progress
New student added: Alice
there is no transaction in progress