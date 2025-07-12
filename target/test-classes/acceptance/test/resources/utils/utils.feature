Feature: Funciones comunes
  Background:
    * def S3Manager = Java.type('co.cobre.lib.qa.aws.S3Util')
    * def S3ManagerInstance = new S3Manager()
    * def FileUtils = Java.type('co.cobre.lib.qa.util.FileUtils')
    * def waitTime = function(seconds) { java.lang.Thread.sleep(seconds * 1000) }

  Scenario: Subir y verificar archivo en S3
    * def result = FileUtils.renameFile(currentFilePath, currentFileName, newPathNewFile, uuid, fileExtension)
    * match result == true
    * def fullFileName = uuid + fileExtension
    * S3ManagerInstance.uploadFileToBucket(bucketName, folderRecaudoFiles, fullFileName, newPathNewFile)
    * def fileExist = S3ManagerInstance.doesFileExist(bucketName, folderRecaudoFiles, fullFileName)
    * match fileExist == true
    * karate.set('fullFileName', fullFileName)

