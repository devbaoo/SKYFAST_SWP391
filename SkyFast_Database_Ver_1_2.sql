CREATE DATABASE SkyFast_2_0;
USE SkyFast_2_0;

CREATE TABLE Airline (
  airline_id                 Integer NOT NULL AUTO_INCREMENT, 
  airline_name        varchar(255) NOT NULL, 
  country varchar(255) NOT NULL, 
  founded_date        date NOT NULL, 
  fleet_size          int NOT NULL, 
  PRIMARY KEY (airline_id));
CREATE TABLE Airplane (
  airplane_id              Integer NOT NULL AUTO_INCREMENT, 
  airplane_name    varchar(255) NOT NULL, 
  seating_capacity int NOT NULL, 
  manufacturer    varchar(255) NOT NULL, 
  diagram         varchar(255) NOT NULL, 
  speed           int NOT NULL, 
  total_length     float NOT NULL, 
  wingspan        float NOT NULL, 
  height          float NOT NULL, 
  status          varchar(255) NOT NULL, 
  airline_id       Integer NOT NULL, 
  PRIMARY KEY (airplane_id));
CREATE TABLE Airport (
  airport_id  Integer NOT NULL AUTO_INCREMENT, 
  airport_code varchar(10) NOT NULL,
  airport_name  varchar(255) NOT NULL, 
  country      varchar(255) NOT NULL, 
  location     varchar(255) NOT NULL, 
  runways_count int NOT NULL, 
  airport_type  varchar(255) NOT NULL,
  PRIMARY KEY (airport_id));
CREATE TABLE Baggage (
  baggage_id          Integer NOT NULL AUTO_INCREMENT, 
  weight      int NOT NULL, 
  baggage_type        varchar(255) NOT NULL, 
  ticket_id    Integer NOT NULL, 
  passenger_id Integer NOT NULL, 
  PRIMARY KEY (baggage_id));
CREATE TABLE Booking (
  booking_id            Integer NOT NULL AUTO_INCREMENT, 
  total_price    int NOT NULL, 
  booking_date   date NOT NULL, 
  payment_status varchar(255) NOT NULL, 
  user_id        Integer NOT NULL, 
  payment_id     Integer NOT NULL, 
  PRIMARY KEY (booking_id));
CREATE TABLE Feedback (
  feedback_id           Integer NOT NULL AUTO_INCREMENT, 
  rating       int NOT NULL, 
  comments     varchar(255), 
  feedback_date timestamp NOT NULL, 
  user_id       Integer NOT NULL, 
  flight_id     Integer NOT NULL, 
  PRIMARY KEY (feedback_id));
CREATE TABLE Flight (
  flight_id            Integer NOT NULL AUTO_INCREMENT, 
  flight_number  varchar(255) NOT NULL, 
  departure_time timestamp NOT NULL, 
  arrival_time   timestamp NOT NULL, 
  duration      float NOT NULL, 
  flight_status        varchar(255) NOT NULL, 
  airline_id     Integer NOT NULL, 
  airplane_id    Integer NOT NULL, 
  route_id       Integer NOT NULL, 
  PRIMARY KEY (flight_id));
CREATE TABLE Maintenance (
  maintenance_id              Integer NOT NULL AUTO_INCREMENT, 
  maintenance_date date NOT NULL, 
  description     varchar(255) NOT NULL, 
  airplane_id      Integer NOT NULL, 
  PRIMARY KEY (maintenance_id));
CREATE TABLE Passenger (
  passenger_id                  Integer NOT NULL AUTO_INCREMENT, 
  title                varchar(255) NOT NULL, 
  full_name             varchar(255) NOT NULL, 
  nationality          varchar(255) NOT NULL, 
  identification_number int NOT NULL, 
  phone_number          int NOT NULL, 
  email                varchar(255) NOT NULL, 
  PRIMARY KEY (passenger_id));
CREATE TABLE Payment (
  payment_id            Integer NOT NULL AUTO_INCREMENT, 
  payment_date   date NOT NULL, 
  payment_method ENUM('Pending', 'Completed', 'Failed') NOT NULL, 
  amount        int NOT NULL, 
  promotion_id   Integer, 
  PRIMARY KEY (payment_id));
CREATE TABLE Promotion (
  promotion_id                 Integer NOT NULL AUTO_INCREMENT, 
  promotion_code               varchar(255) NOT NULL, 
  description        varchar(255) NOT NULL, 
  discount_percentage int NOT NULL, 
  start_date          date NOT NULL, 
  end_date            date NOT NULL, 
  promotion_status             varchar(255) NOT NULL, 
  airline_id          Integer NOT NULL, 
  PRIMARY KEY (promotion_id));
CREATE TABLE Route (
  route_id                 Integer NOT NULL AUTO_INCREMENT, 
  departure_airport_id integer NOT NULL, 
  arrival_airport_id   integer NOT NULL, 
  distance           int NOT NULL, 
  route_status        ENUM('Active', 'Inactive', 'Under Maintenance') NOT NULL DEFAULT 'Active',
  PRIMARY KEY (route_id));
CREATE TABLE Route_Airport (
  airport_id Integer NOT NULL, 
  route_id Integer NOT NULL, 
  PRIMARY KEY (airport_id, 
  route_id));
CREATE TABLE Seat (
  seat_id         Integer NOT NULL AUTO_INCREMENT, 
  seat_number int NOT NULL, 
  class      varchar(255) NOT NULL, 
  seat_status     varchar(255) NOT NULL, 
  airplane_id Integer NOT NULL, 
  PRIMARY KEY (seat_id));
CREATE TABLE Ticket (
  ticket_id          Integer NOT NULL AUTO_INCREMENT, 
  ticket_type  varchar(255) NOT NULL, 
  ticket_status  varchar(255) NOT NULL, 
  booking_id   Integer NOT NULL, 
  seat_id      Integer NOT NULL, 
  flight_id    Integer NOT NULL, 
  passenger_id Integer NOT NULL, 
  PRIMARY KEY (ticket_id));
CREATE TABLE User (
  user_id          Integer NOT NULL AUTO_INCREMENT, 
  user_name    varchar(255) NOT NULL, 
  password    varchar(255) NOT NULL, 
  email       varchar(255) NOT NULL UNIQUE, 
  full_name    varchar(255) NOT NULL, 
  phone_number int, 
  address     varchar(255), 
  role        ENUM ('CUSTOMER', 'ADMIN', 'STAFF', 'MANAGER') NOT NULL, 
  created_at   timestamp, 
  update_at    timestamp, 
  PRIMARY KEY (user_id));
ALTER TABLE Feedback ADD CONSTRAINT FKFeedback374563 FOREIGN KEY (user_id) REFERENCES User (user_Id);
ALTER TABLE Booking ADD CONSTRAINT FKBooking175951 FOREIGN KEY (user_id) REFERENCES User (user_id);
ALTER TABLE Booking ADD CONSTRAINT FKBooking795296 FOREIGN KEY (payment_id) REFERENCES Payment (payment_id);
ALTER TABLE Ticket ADD CONSTRAINT FKTicket882729 FOREIGN KEY (booking_id) REFERENCES Booking (booking_id);
ALTER TABLE Baggage  ADD CONSTRAINT FKBaggage255404 FOREIGN KEY (ticket_id) REFERENCES Ticket (ticket_id);
ALTER TABLE Ticket ADD CONSTRAINT FKTicket981080 FOREIGN KEY (seat_id) REFERENCES Seat (seat_id);
ALTER TABLE Ticket ADD CONSTRAINT FKTicket864787 FOREIGN KEY (flight_id) REFERENCES Flight (flight_id);
ALTER TABLE Flight ADD CONSTRAINT FKFlight999989 FOREIGN KEY (airplane_id) REFERENCES Airplane (airplane_id);
ALTER TABLE Maintenance ADD CONSTRAINT FKMaintenanc506975 FOREIGN KEY (airplane_id) REFERENCES Airplane (airplane_id);
ALTER TABLE Seat ADD CONSTRAINT FKSeat440493 FOREIGN KEY (airplane_id) REFERENCES Airplane (airplane_id);
ALTER TABLE Promotion ADD CONSTRAINT FKPromotion813557 FOREIGN KEY (airline_id) REFERENCES Airline (airline_Id);
ALTER TABLE Airplane ADD CONSTRAINT FKAirplane938463 FOREIGN KEY (airline_id) REFERENCES Airline (airline_id);
ALTER TABLE Flight ADD CONSTRAINT FKFlight442973 FOREIGN KEY (airline_id) REFERENCES Airline (airline_id);
ALTER TABLE Flight ADD CONSTRAINT FKFlight499589 FOREIGN KEY (route_id) REFERENCES Route (route_id);
ALTER TABLE Route_Airport  ADD CONSTRAINT FKRoute_Airp533248 FOREIGN KEY (route_id) REFERENCES Route (route_id);
ALTER TABLE Route_Airport  ADD CONSTRAINT FKRoute_Airp278933 FOREIGN KEY (airport_id) REFERENCES Airport (airport_id);
ALTER TABLE Feedback ADD CONSTRAINT FKFeedback626907 FOREIGN KEY (flight_id) REFERENCES Flight (flight_id);
ALTER TABLE Payment ADD CONSTRAINT FKPayment681653 FOREIGN KEY (promotion_id) REFERENCES Promotion (promotion_id);
ALTER TABLE Ticket ADD CONSTRAINT FKTicket195070 FOREIGN KEY (passenger_id) REFERENCES Passenger (passenger_id);
ALTER TABLE Baggage  ADD CONSTRAINT FKBaggage275706 FOREIGN KEY (passenger_id) REFERENCES Passenger (passenger_id);