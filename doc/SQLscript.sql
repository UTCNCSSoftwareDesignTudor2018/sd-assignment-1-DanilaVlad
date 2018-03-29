-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema studentsmanagement
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema studentsmanagement
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `studentsmanagement` DEFAULT CHARACTER SET utf8 ;
USE `studentsmanagement` ;

-- -----------------------------------------------------
-- Table `studentsmanagement`.`teachers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studentsmanagement`.`teachers` (
  `idteacher` INT(11) NOT NULL,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idteacher`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '	';


-- -----------------------------------------------------
-- Table `studentsmanagement`.`courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studentsmanagement`.`courses` (
  `idcourse` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `teachers_id` INT(11) NOT NULL,
  PRIMARY KEY (`idcourse`, `teachers_id`),
  INDEX `fk_courses_teachers1_idx` (`teachers_id` ASC),
  CONSTRAINT `fk_courses_teachers1`
    FOREIGN KEY (`teachers_id`)
    REFERENCES `studentsmanagement`.`teachers` (`idteacher`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `studentsmanagement`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studentsmanagement`.`students` (
  `idstudent` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `card_nr` VARCHAR(45) NULL DEFAULT NULL,
  `personal_numerical_code` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `group` VARCHAR(45) NULL DEFAULT NULL,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idstudent`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `studentsmanagement`.`enrollments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studentsmanagement`.`enrollments` (
  `exams_id` INT(11) NOT NULL AUTO_INCREMENT,
  `students_id` INT(11) NOT NULL,
  `courses_id` INT(11) NOT NULL,
  PRIMARY KEY (`exams_id`, `students_id`, `courses_id`),
  UNIQUE INDEX `exams_id_UNIQUE` (`exams_id` ASC),
  INDEX `fk_students_has_courses_courses1_idx` (`courses_id` ASC),
  INDEX `fk_students_has_courses_students_idx` (`students_id` ASC),
  INDEX `fk_enrollments_exams1_idx` (`exams_id` ASC),
  CONSTRAINT `fk_students_has_courses_courses1`
    FOREIGN KEY (`courses_id`)
    REFERENCES `studentsmanagement`.`courses` (`idcourse`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_students_has_courses_students`
    FOREIGN KEY (`students_id`)
    REFERENCES `studentsmanagement`.`students` (`idstudent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `studentsmanagement`.`exams`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studentsmanagement`.`exams` (
  `idexam` INT(11) NOT NULL AUTO_INCREMENT,
  `grade` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idexam`),
  UNIQUE INDEX `idexam_UNIQUE` (`idexam` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `studentsmanagement`.`students` (`idstudent`, `name`, `card_nr`, `personal_numerical_code`, `address`, `group`, `username`, `password`) VALUES ('5', 'Camelia', '832891', '8201928198', 'Focsani', '30433', 'cami', 'caine');
INSERT INTO `studentsmanagement`.`students` (`idstudent`, `name`, `card_nr`, `personal_numerical_code`, `address`, `group`, `username`, `password`) VALUES ('1', 'Vlad', '889', '982192', 'Nasaud', '30432', 'vlad', 'pass');
INSERT INTO `studentsmanagement`.`courses` (`idcourse`, `name`, `teachers_id`) VALUES ('4', 'IP', '4');
INSERT INTO `studentsmanagement`.`teachers` (`idteacher`, `username`, `password`, `name`) VALUES ('4', 'teach4', 'pas4', 'Tedi');
INSERT INTO `studentsmanagement`.`exams` (`idexam`, `grade`) VALUES ('8', '8');
INSERT INTO `studentsmanagement`.`enrollments` (`exams_id`, `students_id`, `courses_id`) VALUES ('6', '3', '2');
	