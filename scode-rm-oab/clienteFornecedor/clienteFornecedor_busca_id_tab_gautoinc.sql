SELECT CODAUTOINC, VALAUTOINC FROM GAUTOINC
WHERE CODCOLIGADA = 0
AND CODSISTEMA IN ('F','T')
AND CODAUTOINC LIKE 'IDCFO'
OR CODAUTOINC LIKE 'IDHISTORICO'
OR CODAUTOINC LIKE 'CODCOTACAO2011'