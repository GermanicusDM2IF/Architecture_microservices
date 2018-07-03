--insert into devise(id_devise, nom_devise, code_devise)
--values(1, 'Euro', 'EUR');
--insert into devise(id_devise, nom_devise, code_devise)
--values(2, 'Dollars', 'USD');
--insert into devise(id_devise, nom_devise, code_devise)
--values(3, 'Livre', 'GBP');
--insert into devise(id_devise, nom_devise, code_devise)
--values(4, 'Yen', 'JPY');

insert into taux_change (id_taux_change,devise_source,devise_dest,taux, date_taux)
values(10001,'EUR','USD',1.17, '2018-01-01');
insert into taux_change (id_taux_change,devise_source,devise_dest,taux, date_taux)
values(10002,'EUR','GBP',1.30, '2018-01-01');
insert into taux_change (id_taux_change,devise_source,devise_dest,taux, date_taux)
values(10003,'USD','JPY',0.75, '2018-01-01');