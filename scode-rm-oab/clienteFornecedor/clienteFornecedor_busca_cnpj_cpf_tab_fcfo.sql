SELECT CODCFO, NOME, NOMEFANTASIA, REPLACE(REPLACE(REPLACE(CGCCFO,'.',''),'-',''),'/','') AS CGCCFO, INSCRESTADUAL, RUA, BAIRRO, CIDADE, CODETD, CEP, TELEFONE, FAX, EMAIL, CONTATO, CAMPOLIVRE, CODMUNICIPIO, INSCRMUNICIPAL, IDCFO 
FROM FCFO
WHERE CODCOLIGADA = 1 
AND REPLACE(REPLACE(REPLACE(CGCCFO,'.',''),'-',''),'/','') = ?