UPDATE GTI..tbCdIntegracaoScodeTotvs
SET intIdAutoIncremento = ?,
datDataHoraAtualizacao = getdate()
WHERE vchBancoDeDados LIKE 'CorporeRM'
AND vchTabela LIKE 'TMOV'
AND vchCampo LIKE 'IDMOV'