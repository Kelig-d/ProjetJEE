INSERT INTO Categorie
VALUES ('stade'),
       ('salle de spectacle'),
       ('lieu public'),
       ('centre aquatique');

INSERT INTO Site
VALUES (1,'Piscine municipale','Paris','centre aquatique'),
       (2,'Place de la Bastille','Paris','lieu public'),
       (3,'Palais des Congrès','Paris','salle de spectacle'),
       (4,'Parc des Princes','Paris','stade');

INSERT INTO TypeSession
VALUES ('qualification'),
       ('médailles');

INSERT INTO Discipline
VALUES (1, 'Athlétisme', 1),
       (2, 'Judo', 1),
       (3, 'Basketball', 1),
       (4, 'Badminton', 1),
       (5, 'Natation', 1),
       (6, 'Escrime', 1),
       (7, 'Tir à l\'arc', 1),
       (8, 'Boxe', 1),
       (9, 'Gymnastique artistique', 1),
       (10, 'Tennis', 1),
       (11, 'Trampoline', 0),
       (12, 'Waterpolo', 0)
       ;


insert into Epreuve
values (1,'100m',1),
       (2,'110m haies',1),
       (3,'3000m steeple',1),
       (4,'relais 4x100m',1),
       (5,'-48kg',2),
       (6,'-57kg',2),
       (7,'-66kg',2),
       (8,'-81kg',2),
       (9,'-100kg',2),
       (10,'+100kg',2),
       (11,'tournoi à 12 équipes',3),
       (12,'tournois simple',4),
       (13,'tournois double',4),
       (14,'tournois double mixte',4),
       (15,'50m nage libre',5),
       (16,'100m dos',5),
       (17,'200m brasse',5),
       (18,'relais 4x100m nage libre',5),
       (19,'épée individuel',6),
       (20,'épée collectif',6),
       (21,'sabre individuel',6),
       (22,'sabre collectif',6),
       (23,'individuel',7),
       (24,'par équipe',7),
       (25,'-69kg',8),
       (26,'-75kg',8),
       (27,'-91kg',8),
       (28,'+91kg',8),
       (29,'poutre',9),
       (30,'anneaux',9),
       (31,'barres asymétriques',9),
       (32,'barre fixe',9),
       (33,'tournois simple',10),
       (34,'tournois double',10),
       (35,'tournois double mixte',10),
       (36,'individuel',11),
       (37,'tournoi',12);