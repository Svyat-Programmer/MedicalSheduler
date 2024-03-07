This backend application works with table data(Clinician Plan) and creates tasks for a Nurse(coding task https://docs.google.com/document/d/1DRoDokcPQPaA6trN8JJ3oLRRROfqS_EE-qHzDamvChE/edit?usp=sharing)
This application checks a table every 20 sec.
Running Instructions
1.Download ZIP from GitHub
2.Unzip
3.Enter folder with unzipped project
4.Run command java -jar out/artifacts/MedicalScheduler_jar/MedicalScheduler.jar. As a resul

I use this script to create a plans:

INSERT INTO treatment_plan (ID, treatment_action, patient, start_time, end_time, days, hours) VALUES (1, 'ACTION_A', 'Svyat','2024-03-15T08:00:00', '2024-03-25T08:00:00', ARRAY['WEDNESDAY', 'FRIDAY'], ARRAY[8, 12, 16]);
INSERT INTO treatment_plan (ID,treatment_action, patient, start_time, end_time, days, hours) VALUES (5, 'ACTION_A', 'Alex','2024-03-20T13:00:00', '2024-03-30T13:00:00', ARRAY['FRIDAY', 'MONDAY'], ARRAY[13, 17, 21]);
INSERT INTO treatment_plan (ID,treatment_action, patient, start_time, end_time, days, hours) VALUES (2, 'ACTION_B', 'John','2024-03-16T09:00:00', '2024-03-26T09:00:00', ARRAY['MONDAY', 'THURSDAY'], ARRAY[9, 13, 17]);
INSERT INTO treatment_plan (ID,treatment_action, patient, start_time, end_time, days, hours) VALUES (3, 'ACTION_B', 'Anna','2024-03-17T10:00:00', '2024-03-27T10:00:00', ARRAY['TUESDAY', 'FRIDAY'], ARRAY[10, 14, 18]);
INSERT INTO treatment_plan (ID,treatment_action, patient, start_time, end_time, days, hours) VALUES (4, 'ACTION_A', 'Mike','2024-03-18T11:00:00', '2024-03-28T11:00:00', ARRAY['WEDNESDAY', 'SATURDAY'], ARRAY[11, 15, 19]);
INSERT INTO treatment_plan (ID,treatment_action, patient, start_time, end_time, days, hours) VALUES (6, 'ACTION_B', 'Sophia','2024-03-25T18:00:00', '2024-04-04T18:00:00', ARRAY['WEDNESDAY', 'SATURDAY'], ARRAY[18, 22, 2]);

