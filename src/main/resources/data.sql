
INSERT INTO driver (name,surname,car, bank_account_number, client_type) VALUES ('Jan','Nowak','Fjat','182378491',0);
INSERT INTO driver (name,surname,car, bank_account_number, client_type) VALUES ('Marcin','Zsynchronizowany','Wolwo','982871823',1);

INSERT INTO parking_meter(occupied,end_time,start_time) VALUES (FALSE ,NULL ,NULL );
INSERT INTO parking_meter(occupied,end_time,start_time) VALUES (FALSE ,NULL ,NULL );

INSERT INTO parking_event (payment_date, payment_pln) VALUES ('2017-10-20 12:12:12.1',5.0);
INSERT INTO parking_event (payment_date, payment_pln) VALUES ('2017-10-20 18:12:12.1',10.0);
INSERT INTO parking_event (payment_date, payment_pln) VALUES ('2017-10-21 16:16:16.1',7.0);