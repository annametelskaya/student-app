-- MySQL Script generated by MySQL Workbench
-- Ср 14 ноя 2018 21:34:44
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema BEGANSS
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BEGANSS
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BEGANSS` DEFAULT CHARACTER SET utf8 ;
USE `BEGANSS` ;

-- -----------------------------------------------------
-- Table `BEGANSS`.`GROUP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BEGANSS`.`GROUP` (
  `GROUP_NUMBER` VARCHAR(3) NOT NULL,
  `AVG_MARK` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`GROUP_NUMBER`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BEGANSS`.`PROFESS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BEGANSS`.`PROFESS` (
  `PROFESS_ID` INT NOT NULL,
  `FIRST_NAME` VARCHAR(10) NOT NULL,
  `SECOND_NAME` VARCHAR(20) NOT NULL,
  `AVG_MARK` DECIMAL(4,3) NOT NULL,
  PRIMARY KEY (`PROFESS_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BEGANSS`.`STUDY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BEGANSS`.`STUDY` (
  `STUDY_ID` INT NOT NULL,
  `NAME` VARCHAR(20) NOT NULL,
  `HOURS` INT NOT NULL,
  `PROFESS_ID` INT NOT NULL,
  `AVG_MARK` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`STUDY_ID`),
  INDEX `fk_STUDY_PROFESS_idx` (`PROFESS_ID` ASC),
  CONSTRAINT `fk_STUDY_PROFESS`
    FOREIGN KEY (`PROFESS_ID`)
    REFERENCES `BEGANSS`.`PROFESS` (`PROFESS_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BEGANSS`.`STUDENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BEGANSS`.`STUDENT` (
  `STUDENT_ID` INT NOT NULL,
  `FIRST_NAME` VARCHAR(10) NOT NULL,
  `SECOND_NAME` VARCHAR(20) NOT NULL,
  `AVG_MARK` VARCHAR(45) NOT NULL,
  `GROUP_NUMBER` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`STUDENT_ID`),
  INDEX `fk_STUDENT_GROUP1_idx` (`GROUP_NUMBER` ASC),
  CONSTRAINT `fk_STUDENT_GROUP1`
    FOREIGN KEY (`GROUP_NUMBER`)
    REFERENCES `BEGANSS`.`GROUP` (`GROUP_NUMBER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BEGANSS`.`MARK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BEGANSS`.`MARK` (
  `MARK_ID` INT NOT NULL,
  `STUDY_ID` INT NOT NULL,
  `STUDENT_ID` INT NOT NULL,
  `DATE` DATE NOT NULL,
  `PROFESS_ID` INT NOT NULL,
  `MARK` INT NOT NULL,
  `COMMENTS` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`MARK_ID`),
  INDEX `fk_MARK_STUDY1_idx` (`STUDY_ID` ASC),
  INDEX `fk_MARK_STUDENT1_idx` (`STUDENT_ID` ASC),
  INDEX `fk_MARK_PROFESS1_idx` (`PROFESS_ID` ASC),
  CONSTRAINT `fk_MARK_STUDY1`
    FOREIGN KEY (`STUDY_ID`)
    REFERENCES `BEGANSS`.`STUDY` (`STUDY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MARK_STUDENT1`
    FOREIGN KEY (`STUDENT_ID`)
    REFERENCES `BEGANSS`.`STUDENT` (`STUDENT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MARK_PROFESS1`
    FOREIGN KEY (`PROFESS_ID`)
    REFERENCES `BEGANSS`.`PROFESS` (`PROFESS_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
