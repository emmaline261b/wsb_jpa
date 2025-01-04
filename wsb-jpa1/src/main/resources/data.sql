-- Wstawianie danych do tabeli Address
insert into address (address_line1, address_line2, city, postal_code)
values
    ('Ul. Leśna 10', 'Mieszkanie 2', 'Warszawa', '00-001'),
    ('Ul. Długa 4', 'Mieszkanie 3', 'Kraków', '31-001');

-- Wstawianie danych do tabeli Doctor
insert into doctor (first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
values
    ('Jan', 'Kowalski', '500100200', 'jan.kowalski@szpital.pl', 'L001', 'SURGEON', 1),
    ('Anna', 'Nowak', '600200300', 'anna.nowak@szpital.pl', 'L002', 'DERMATOLOGIST', 2);

-- Wstawianie danych do tabeli Patient
insert into patient (first_name, last_name, pronoun, telephone_number, email, patient_number, date_of_birth, address_id)
values
    ('Maria', 'Wiśniewska', 'SHE', '700300400', 'maria.wisniewska@gmail.com', 'P001', '1992-04-15', 1),
    ('Tomasz', 'Zieliński', 'HE', '800400500', 'tomasz.zielinski@gmail.com', 'P002', '1980-09-12', 2);

-- Wstawianie danych do tabeli Visit
insert into visit (description, time, doctor_id, patient_id)
values
    ('Konsultacja chirurgiczna', '2024-12-05T09:00:00', 1, 1),
    ('Badanie dermatologiczne', '2024-12-06T14:30:00', 2, 2);

-- Wstawianie danych do tabeli MedicalTreatment
insert into medical_treatment (description, type, visit_id)
values
    ('USG jamy brzusznej', 'USG', 1),
    ('RTG klatki piersiowej', 'RTG', 2);
