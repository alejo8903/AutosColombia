CREATE TABLE Vehicle (
   id plate VARCHAR(10) PRIMARY KEY
);

CREATE TABLE Register (
  id INT PRIMARY KEY AUTO_INCREMENT,
  date_entry DATETIME,
  date_exit DATETIME,
  total_time INT,
  plate VARCHAR(10),
  FOREIGN KEY (placa) REFERENCES Vehicle(placa)
);