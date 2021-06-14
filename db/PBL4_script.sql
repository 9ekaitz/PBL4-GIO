USE biltegia;
DROP PROCEDURE IF EXISTS acceptOrder;
DELIMITER //
CREATE PROCEDURE acceptOrder(IN ID INT UNSIGNED)
BEGIN
	START TRANSACTION;
	SELECT * INTO @id, @sData, @helbidea, @bezeroa FROM eskaera_onartzeko WHERE eskaeraId = ID;
	INSERT INTO eskaera_onartuta (eskaeraId,sortze_data, helbidea, bezeroa) VALUES (@id,@sData,@helbidea,@bezeroa);
	COMMIT;
END//
DELIMITER ;

DROP PROCEDURE IF EXISTS rejectOrder;
DELIMITER //
CREATE PROCEDURE rejectOrder(IN ID INT UNSIGNED)
BEGIN
	START TRANSACTION;
	DELETE FROM lerroak_onartzeko WHERE eskaeraId = ID;
	DELETE FROM eskaera_onartzeko WHERE eskaeraId = ID;
	COMMIT;
END//
DELIMITER ;


DROP TRIGGER IF EXISTS after_onartuta_insert;

DELIMITER //
CREATE TRIGGER after_onartuta_insert
	BEFORE INSERT ON eskaera_onartuta
	FOR EACH ROW BEGIN
		INSERT INTO lerroak_onartuta SELECT * FROM lerroak_onartzeko WHERE eskaeraId = NEW.eskaeraId;
	
		DELETE FROM lerroak_onartzeko WHERE eskaeraId = NEW.eskaeraId;
		DELETE FROM eskaera_onartzeko WHERE eskaeraId = NEW.eskaeraId;
	END//
	
DELIMITER ;

DROP TRIGGER IF EXISTS after_tenperatura_insert;

DELIMITER //
CREATE TRIGGER after_tenperatura_insert
    BEFORE INSERT ON tenperatura
    FOR EACH ROW BEGIN
        SELECT HOUR(sortze_ordua) INTO @t FROM tenperatura LIMIT 1;
           IF HOUR(NEW.sortze_ordua) != @t THEN
               INSERT INTO tenperatura_BatazBesteko VALUES (current_date(), (SELECT sortze_ordua  FROM tenperatura LIMIT 1), (SELECT AVG(tenperatura) FROM tenperatura WHERE HOUR(sortze_ordua) = @t));
           END IF;
       
    END//
    
DELIMITER ;

DROP PROCEDURE IF EXISTS checkAndDelete;

DELIMITER //
CREATE PROCEDURE checkAndDelete()
   	BEGIN
   		DELETE FROM tenperatura WHERE HOUR(sortze_ordua) = (SELECT HOUR(sortze_ordua) FROM tenperatura_BatazBesteko ORDER BY sortze_ordua DESC LIMIT 1);
    END//
    
DELIMITER ;