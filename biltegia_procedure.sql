USE biltegia;
DROP PROCEDURE IF EXISTS acceptOrder;
DELIMITER //
CREATE PROCEDURE acceptOrder(IN ID INT UNSIGNED)
BEGIN
    SELECT * INTO @id, @sData, @helbidea, @bezeroa FROM eskaera_onartzeko WHERE eskaeraId = ID;
    INSERT INTO eskaera_onartuta VALUES (@id,@sData,@helbidea,@bezeroa);
END//
DELIMITER ;

DROP TRIGGER IF EXISTS after_onartuta_insert;

DELIMITER //
CREATE TRIGGER after_onartuta_insert
    AFTER INSERT ON eskaera_onartuta
    FOR EACH ROW BEGIN
        WITH lerroak AS (SELECT * FROM eskaera_onartzeko e WHERE eskaeraId = NEW.eskaeraid)

        INSERT INTO lerroak_onartuta SELECT * FROM lerroak_onartzeko;
    
        DELETE FROM lerroak_onartzeko WHERE eskaeraId = NEW.eskaeraId;
        DELETE FROM eskaera_onartzeko WHERE eskaeraId = NEW.eskaeraId;
    END//
    
DELIMITER ;