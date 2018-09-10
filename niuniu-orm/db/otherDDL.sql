DELIMITER $$
CREATE DEFINER=`root`@`%` PROCEDURE `GetParentAccount`(IN startParent VARCHAR(20))
BEGIN
	SELECT Accountid, UserID, UserName, Limited, Ratio, Percentage, State, T2.SupUserName, Level, 
    OffType, Money, Attribute1, Attribute2
FROM (
    SELECT 
        @r AS _id,
        (SELECT @r := SupUserName FROM account_detail WHERE username = _id order by Level) AS SupUserName,
        @l := @l + 1 AS lvl
    FROM
        (SELECT @r := startParent, @l := 0) vars,
        account_detail h
    WHERE @r <> 'system'

    ) T1
JOIN account_detail T2
ON T1._id = T2.username
ORDER BY T1.lvl DESC;

END$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `getItemNameByNo`(p_itemNo char(255)) RETURNS char(255) CHARSET utf8
BEGIN  
DECLARE itemName CHAR(255);  

SET itemName=(SELECT itemNameCN FROM lottery_item WHERE itemNo = p_itemNo and lotteryType='01');  
IF itemName is null THEN  
SET itemName='';    
END IF;    
RETURN itemName;  
END$$
DELIMITER ;


ALTER TABLE `lottery`.`lottery_order` 
ADD COLUMN `SYSTEMAMOUNT` DOUBLE(18,2) NULL AFTER `RETURNAMOUNT`;

ALTER TABLE `lottery`.`lottery_order_detail` 
CHANGE COLUMN `ITEMODDS` `ITEMSCALE` DOUBLE(18,4) NULL DEFAULT NULL COMMENT '赔率' ;

ALTER TABLE `lottery`.`lottery_item` 
DROP COLUMN `ITEMBONUS`,
DROP COLUMN `ITEMODDS`,
CHANGE COLUMN `ITEMNAME` `WINITEM` VARCHAR(50) NULL DEFAULT NULL COMMENT '赢项' ,
ADD COLUMN `DRAWITEM` VARCHAR(50) NULL COMMENT '和局' AFTER `ItemNameCN`;

ALTER TABLE `lottery`.`lottery_item` 
CHANGE COLUMN `DRAWITEM` `DRAWITEM` VARCHAR(50) NULL DEFAULT NULL COMMENT '和局' AFTER `WINITEM`;

UPDATE `lottery`.`lottery_item` SET `WINITEM`='1', `DRAWITEM`='2' WHERE `ITEMID`='5';
UPDATE `lottery`.`lottery_item` SET `WINITEM`='1', `DRAWITEM`='3' WHERE `ITEMID`='6';
UPDATE `lottery`.`lottery_item` SET `WINITEM`='1', `DRAWITEM`='4' WHERE `ITEMID`='7';
UPDATE `lottery`.`lottery_item` SET `WINITEM`='2', `DRAWITEM`='2' WHERE `ITEMID`='8';
UPDATE `lottery`.`lottery_item` SET `WINITEM`='2', `DRAWITEM`='4' WHERE `ITEMID`='9';
UPDATE `lottery`.`lottery_item` SET `WINITEM`='2', `DRAWITEM`='3' WHERE `ITEMID`='10';
UPDATE `lottery`.`lottery_item` SET `WINITEM`='3', `DRAWITEM`='2' WHERE `ITEMID`='11';
UPDATE `lottery`.`lottery_item` SET `WINITEM`='3', `DRAWITEM`='1' WHERE `ITEMID`='12';
UPDATE `lottery`.`lottery_item` SET `WINITEM`='3', `DRAWITEM`='4' WHERE `ITEMID`='13';
UPDATE `lottery`.`lottery_item` SET `WINITEM`='4', `DRAWITEM`='1' WHERE `ITEMID`='14';
UPDATE `lottery`.`lottery_item` SET `WINITEM`='4', `DRAWITEM`='2' WHERE `ITEMID`='15';
UPDATE `lottery`.`lottery_item` SET `WINITEM`='4', `DRAWITEM`='3' WHERE `ITEMID`='16';
UPDATE `lottery`.`lottery_item` SET `DRAWITEM`='2,4' WHERE `ITEMID`='21';
UPDATE `lottery`.`lottery_item` SET `DRAWITEM`='1,3' WHERE `ITEMID`='22';
UPDATE `lottery`.`lottery_item` SET `DRAWITEM`='2,4' WHERE `ITEMID`='23';
UPDATE `lottery`.`lottery_item` SET `DRAWITEM`='1,3' WHERE `ITEMID`='24';


ALTER TABLE `lottery`.`lottery_round_item` 
DROP COLUMN `ITEMBONUS`,
DROP COLUMN `ITEMODDS`;


ALTER TABLE `lottery`.`lottery_order` 
ADD COLUMN `AGENCYRETURN` DOUBLE(18,2) NULL COMMENT '代理返利' AFTER `RETURNAMOUNT`;

