CREATE TABLE department (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    department VARCHAR(255) NOT NULL
);

CREATE TABLE inventory (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    amount INTEGER NOT NULL,
    departmentFK INTEGER,
    FOREIGN KEY (departmentFK) REFERENCES department(id)
);

INSERT INTO department (id, department) VALUES (1, 'Human Resources');
INSERT INTO department (id, department) VALUES (2, 'Marketing');
INSERT INTO department (id, department) VALUES (3, 'Finance');
