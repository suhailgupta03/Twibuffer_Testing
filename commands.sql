USE twibuffer;
CREATE TABLE usercred(id INT,firstname VARCHAR(255),lastname VARCHAR(255),username VARCHAR(255),PASSWORD VARCHAR(255),email VARCHAR(255),
		      accesstoken VARCHAR(255),PRIMARY KEY(id)
		     );
		     
SELECT * FROM usercred;		     
ALTER TABLE usercred ADD accesstokensecret VARCHAR(255);
UPDATE usercred SET accesstoken=NULL WHERE id=1;
UPDATE usercred SET accesstokensecret=NULL WHERE id=1;

CREATE TABLE scheduled(sid INT,id INT,tweet VARCHAR(255),sdate DATE, stime TIME,PRIMARY KEY(sid),FOREIGN KEY(id) REFERENCES usercred(id));

SELECT * FROM scheduled;

DELETE FROM scheduled WHERE id=1;
SELECT tweet FROM scheduled WHERE stime <= CURTIME();
SELECT NOW();
SELECT CURTIME();
SELECT CURDATE();
SELECT sdate FROM scheduled WHERE sdate = CURDATE();
SELECT tweet FROM scheduled WHERE stime <= CURTIME() AND sdate = CURDATE();
