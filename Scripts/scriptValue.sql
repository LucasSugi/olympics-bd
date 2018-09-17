INSERT INTO MODALIDADE VALUES (1,'Futebol','Jogo realizado por dois times, cada um com 11 jogadores de cada lado. O objetivo dos times é realizar gols');
INSERT INTO MODALIDADE VALUES (2,'Corrida 100M rasos','Modalidade de corrida de velocidade presente no atletismo');
INSERT INTO MODALIDADE VALUES (3,'Salto com Vara','Modalidade do atletismo onde o objetivo dos atletas é passar por cima de uma barra ou sarrafo utilizando uma vara longa flexível');

INSERT INTO NACAO VALUES ('Brasil','America',100,'home/user/Desktop/brasil.png','Ouviram do Ipiranga as margens plácidas De um povo heroico o brado retumbante E o sol da Liberdade, em raios fúlgidos Brilhou no céu da Pátria nesse instante',1);
INSERT INTO NACAO VALUES('Estados Unidos','America',200,'home/user/Desktop/eua.png','Oh, say! can you see by the dawns early light What so proudly we hailed at the twilights last gleaming; Whose broad stripes and bright stars, through the perilous fight, O er the ramparts we watched were so gallantly streaming? And the rockets red glare, the bombs bursting in air, Gave proof through the night that our flag was still there: Oh, say! does that star-spangled banner yet wave O er the land of the free and the home of the brave?',2);
INSERT INTO NACAO VALUES('China','Asia',200,'home/user/Desktop/china.png','Qilai! Buyuan zuo nuli de renmen, Ba women de xuerou zhucheng women xin de changcheng. Zhonghua Minzu dao liao zui weixian de shihou, Meigeren beipo zhe fachu zuihou de housheng. Qilai! Qilai! Qilai! Women wanzhong yixin, Mao zhe diren de paohuo, Qianjin! Mao zhe diren de paohuo, Qianjin! Qianjin! Qianjin! Jin',3);

INSERT INTO ATLETA VALUES ('YA104440','Neymar da Silva Santos Junior', TO_DATE('05/02/1992', 'DD/MM/RRRR'), 175, 70, 'M', 'Brasil');
INSERT INTO ATLETA VALUES ('XH214495','Tyson Joe Gay', TO_DATE('09/08/1982', 'DD/MM/RRRR'), 180, 75,'M','Estados Unidos');
INSERT INTO ATLETA VALUES ('DW328401','Xue Changrui', TO_DATE('31/05/1991', 'DD/MM/RRRR'), 188, 75, 'M','China');

INSERT INTO DISPUTA VALUES('YA104440',1);
INSERT INTO DISPUTA VALUES('XH214495',2);
INSERT INTO DISPUTA VALUES('DW328401',3);

INSERT INTO ESPORTISTA VALUES ('YA104440',1,'Regular');
INSERT INTO ESPORTISTA VALUES ('XH214495',2,'Regular');
INSERT INTO ESPORTISTA VALUES ('DW328401',3,'Regular');

INSERT INTO PREPARADOR VALUES(10,'Tite','Caxias do Sul','Rio Grande do Sul','Brasil','M',25,05,1961,'titeMito@gmail.com','1234',49212390854,'HNS012153');
INSERT INTO PREPARADOR VALUES(20,'Bruce Arena','Brooklyn','New York','Estados Unidos','M',21,09,1951,'brunoArena@gmail.com','9090',46312210189,'XDS419956');
INSERT INTO PREPARADOR VALUES(30,'Lang Ping','Pequim','Pequim','China','F',10,12,1960,'langPing@gmail.com','1111',29416330782,'PKS118355');

INSERT INTO TELEFONE_PREPARADOR VALUES (10,11,885516324);
INSERT INTO TELEFONE_PREPARADOR VALUES (20,16,123216339);
INSERT INTO TELEFONE_PREPARADOR VALUES (30,19,905517141);

INSERT INTO ATIVIDADE_RECUPERACAO VALUES(100,'Caminhada de forma moderada para recuperacao ativa');
INSERT INTO ATIVIDADE_RECUPERACAO VALUES(200,'Natacao para auxiliar no alivio da tensao');
INSERT INTO ATIVIDADE_RECUPERACAO VALUES(300,'Ciclismo de forma moderada para recuperacao ativa');

INSERT INTO MODO_PREPARO VALUES (1000,'Ajustar a esteira em uma velocidade compatível com a recuperação do atleta');
INSERT INTO MODO_PREPARO VALUES (2000,'Aquecer a agua da piscina para aliviar a tensao musculares do atleta, o mesmo deve usar touca e oculos');
INSERT INTO MODO_PREPARO VALUES (3000,'Ajustar a bicicleta em uma velocidade compatível com a recuperação do atleta');

INSERT INTO TREINO VALUES(11,'Caminhada na pista de corrida',1000,100);
INSERT INTO TREINO VALUES(12,'Nado na piscina aquecida',2000,200);
INSERT INTO TREINO VALUES(13,'Bicleta na academia',3000,300);

INSERT INTO ROTINA_TREINO VALUES ('YA104440',1,'SEGUNDA',10,'10:00',11);
INSERT INTO ROTINA_TREINO VALUES ('XH214495',2,'TERCA',20,'11:00',12);
INSERT INTO ROTINA_TREINO VALUES ('DW328401',3,'QUARTA',30,'08:00',13);

INSERT INTO MEDICO VALUES (21,'Chico Garcia',045904,'244538967');
INSERT INTO MEDICO VALUES (31,'Joesley Batista',035003,'534532190');
INSERT INTO MEDICO VALUES (41,'Marinho Andrade',145729,'217536332');

INSERT INTO ENDERECO_MEDICO VALUES(21,'Av Sao Carlos',NULL,NULL);
INSERT INTO ENDERECO_MEDICO VALUES(31,'Av Barrretos',NULL,NULL);
INSERT INTO ENDERECO_MEDICO VALUES(41,'Rua Achille Bassi',NULL,NULL);

INSERT INTO TELEFONE_MEDICO VALUES(21,11,725590590);
INSERT INTO TELEFONE_MEDICO VALUES(31,16,545573593);
INSERT INTO TELEFONE_MEDICO VALUES(41,19,128716594);

INSERT INTO LESAO VALUES (101,'Estiramento Muscular');
INSERT INTO LESAO VALUES (102,'Distencao Muscular');
INSERT INTO LESAO VALUES (103,'Contusao Muscular');

INSERT INTO TESTE_DOPING VALUES(201,'Exame de urina',24,11,2016,'Negativo');
INSERT INTO TESTE_DOPING VALUES(202,'Exame de urina',25,11,2016,'Positivo');
INSERT INTO TESTE_DOPING VALUES(203,'Exame de urina',26,11,2016,'Negativo');

INSERT INTO REALIZA VALUES ('YA104440',1,201,21);
INSERT INTO REALIZA VALUES ('XH214495',2,202,31);
INSERT INTO REALIZA VALUES ('DW328401',3,203,41);

INSERT INTO CONSULTA VALUES ('YA104440',21);
INSERT INTO CONSULTA VALUES ('XH214495',31);
INSERT INTO CONSULTA VALUES ('DW328401',41);

INSERT INTO DIAGNOSTICO VALUES(301,'O atleta possui uma lesao muscular na panturrilha');
INSERT INTO DIAGNOSTICO VALUES(302,'O atleta possui um estiramento muscular na coxa');
INSERT INTO DIAGNOSTICO VALUES(303,'O atleta possui uma contusao muscular perto do calcanhar');

INSERT INTO ATENDIMENTO VALUES (401,'YA104440',21,301,12,11,2016);
INSERT INTO ATENDIMENTO VALUES (402,'XH214495',31,302,13,11,2016);
INSERT INTO ATENDIMENTO VALUES (403,'DW328401',41,303,14,11,2016);

INSERT INTO SINTOMA VALUES(501,'O atleta sente fortes dores musculares na panturrilha ao andar');
INSERT INTO SINTOMA VALUES(502,'O atleta sente fortes dores na coxa, mesmo sem nenhuma movimentação');
INSERT INTO SINTOMA VALUES(503,'O atleta sente nauseas e dor de cabeça');

INSERT INTO SINTOMAS_PACIENTES VALUES (501,401);
INSERT INTO SINTOMAS_PACIENTES VALUES (502,402);
INSERT INTO SINTOMAS_PACIENTES VALUES (503,403);

INSERT INTO METODO_TRATAMENTO VALUES (601,'AS','KS',1,1);
INSERT INTO METODO_TRATAMENTO VALUES (602,'AS','AS',1,1);
INSERT INTO METODO_TRATAMENTO VALUES (603,'AS','AS',1,1);

INSERT INTO DIAGNOSTICO_METODO VALUES(301,601);
INSERT INTO DIAGNOSTICO_METODO VALUES(302,602);
INSERT INTO DIAGNOSTICO_METODO VALUES(303,603);

INSERT INTO TRATAMENTO VALUES ('YA104440',1,21,101);
INSERT INTO TRATAMENTO VALUES ('XH214495',2,31,102);
INSERT INTO TRATAMENTO VALUES ('DW328401',3,41,103);
