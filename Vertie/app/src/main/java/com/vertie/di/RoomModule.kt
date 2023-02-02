package com.vertie.di

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vertie.data.Database
import com.vertie.Constants
import com.vertie.di.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
class RoomModule {
    @AppScope
    @Provides
    fun provideRoomDatabase(app: Application): com.vertie.data.Database {
//        val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("CREATE TABLE IF NOT EXISTS `inventory_check` (`inventoryCheckID` TEXT NOT NULL, `assetLocationID` TEXT, `inventoryCheckStatusID` TEXT NOT NULL, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`inventoryCheckID`), FOREIGN KEY(`inventoryCheckStatusID`) REFERENCES `inventory_check_status`(`inventoryCheckStatusID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`assetLocationID`) REFERENCES `asset_location`(`assetLocationID`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
//                database.execSQL("CREATE TABLE IF NOT EXISTS `inventory_check_status` (`inventoryCheckStatusID` TEXT NOT NULL, `inventoryCheckStatusName` TEXT, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`inventoryCheckStatusID`))")
//                database.execSQL("CREATE TABLE IF NOT EXISTS `inventory_check_asset` (`inventoryCheckAssetID` TEXT NOT NULL, `inventoryCheckID` TEXT NOT NULL, `assetID` TEXT NOT NULL, `inventoryCheckAssetStatusID` TEXT NOT NULL, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`inventoryCheckAssetID`), FOREIGN KEY(`inventoryCheckID`) REFERENCES `inventory_check`(`inventoryCheckID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`assetID`) REFERENCES `asset`(`assetID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`inventoryCheckAssetStatusID`) REFERENCES `inventory_check_asset_status`(`inventoryCheckAssetStatusID`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
//                database.execSQL("CREATE TABLE IF NOT EXISTS `inventory_check_asset_status` (`inventoryCheckAssetStatusID` TEXT NOT NULL, `inventoryCheckAssetStatusName` TEXT, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`inventoryCheckAssetStatusID`))")
//            }
//        }
//        val MIGRATION_2_3 = object : Migration(2, 3) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE 'asset' ADD COLUMN 'isSynched' INTEGER DEFAULT NULL")
//            }
//        }
//
//        val MIGRATION_3_4 = object : Migration(3, 4) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                // workflow tables
//                database.execSQL("CREATE TABLE IF NOT EXISTS `workflow` (`workFlowID` TEXT NOT NULL, `workFlowName` TEXT, `isActive` INTEGER, `isDefault` INTEGER, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`workFlowID`))")
//                database.execSQL("CREATE TABLE IF NOT EXISTS `workflow_step` (`workFlowStepID` TEXT NOT NULL, `workFlowID` TEXT, `workFlowStepOrder` TEXT, `workFlowStepName` TEXT, `isEndState` TEXT, `assetStatusID` TEXT, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`workFlowStepID`), FOREIGN KEY(`workFlowID`) REFERENCES `workflow`(`workFlowID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`assetStatusID`) REFERENCES `asset_status`(`assetStatusID`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
//                database.execSQL("CREATE TABLE IF NOT EXISTS `workflow_step_action` (`workFlowStepActionID` TEXT NOT NULL, `workFlowStepActionLabel` TEXT, `setLocationNull` INTEGER, `workFlowStepID` TEXT, `destinationWorkFlowStepID` TEXT, `allowNotifications` INTEGER, `groupID` TEXT, `assetLocationID` TEXT, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`workFlowStepActionID`), FOREIGN KEY(`workFlowStepID`) REFERENCES `workflow_step`(`workFlowStepID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`destinationWorkFlowStepID`) REFERENCES `workflow_step`(`workFlowStepID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`assetLocationID`) REFERENCES `asset_location`(`assetLocationID`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
//
//                //add asset workflow FOREIGN KEYS
//                database.execSQL(
//                    "CREATE TABLE IF NOT EXISTS `asset_new` (`assetID` TEXT NOT NULL, `assetTypeID` TEXT, `assetCode` TEXT, `assetDescription` TEXT, `bundleSize` INTEGER, `assetLat` REAL, `assetLong` REAL, `assetStatusID` TEXT, `assetLocationID` TEXT, `tagID` TEXT, `imageUrl` TEXT, `partNo` TEXT, `manufacturer` TEXT, `modelNo` TEXT, `currentWorkFlowStepID` TEXT, `workFlowID` TEXT, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `isSynched` INTEGER, `dataVersion` REAL, PRIMARY KEY(`assetID`), FOREIGN KEY(`assetTypeID`) REFERENCES `asset_type`(`assetTypeID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`assetStatusID`) REFERENCES `asset_status`(`assetStatusID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`assetLocationID`) REFERENCES `asset_location`(`assetLocationID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`tagID`) REFERENCES `tag`(`tagID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`workFlowID`) REFERENCES `workflow`(`workFlowID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`currentWorkFlowStepID`) REFERENCES `workflow_step`(`workFlowStepID`) ON UPDATE NO ACTION ON DELETE NO ACTION )"
//                )
//                database.execSQL("INSERT INTO`asset_new` (`assetID`, `assetTypeID` , `assetCode`, `assetDescription` , `bundleSize` , `assetLat` , `assetLong` , `assetStatusID` , `assetLocationID` , `tagID` , `imageUrl` , `partNo` , `manufacturer` , `modelNo` , `creationDate` , `modificationDate` , `createdBy` , `modifiedBy` , `associatedCompanyID` , `isDeleted` , `isSynched` , `dataVersion` )SELECT `assetID`, `assetTypeID` , `assetCode`, `assetDescription` , `bundleSize` , `assetLat` , `assetLong` , `assetStatusID` , `assetLocationID` , `tagID` , `imageUrl` , `partNo` , `manufacturer` , `modelNo` , `creationDate` , `modificationDate` , `createdBy` , `modifiedBy` , `associatedCompanyID` , `isDeleted` , `isSynched` , `dataVersion` FROM  `asset`")
//                database.execSQL("DROP TABLE asset")
//                database.execSQL("ALTER TABLE asset_new RENAME TO asset")
//
//                // custom field tables
//                database.execSQL("CREATE TABLE IF NOT EXISTS `data_type` (`dataTypeID` TEXT NOT NULL, `dataTypeName` TEXT, `hasChoices` INTEGER, `hasInputLimit` INTEGER, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`dataTypeID`))")
//                database.execSQL("CREATE TABLE IF NOT EXISTS `custom_field` (`customFieldID` TEXT NOT NULL, `customFieldName` TEXT, `dataTypeID` TEXT NOT NULL, `inputLimit` INTEGER, `customFieldOrder` INTEGER, `forAllTypes` INTEGER, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`customFieldID`), FOREIGN KEY(`dataTypeID`) REFERENCES `data_type`(`dataTypeID`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
//                database.execSQL("CREATE TABLE IF NOT EXISTS `custom_field_choice` (`customFieldChoiceID` TEXT NOT NULL, `customFieldChoiceValue` TEXT, `customFieldID` TEXT NOT NULL, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`customFieldChoiceID`), FOREIGN KEY(`customFieldID`) REFERENCES `custom_field`(`customFieldID`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
//                database.execSQL("CREATE TABLE IF NOT EXISTS `asset_type_field` (`assetTypeFieldID` TEXT NOT NULL, `isRequired` INTEGER, `customFieldID` TEXT, `assetTypeID` TEXT, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`assetTypeFieldID`), FOREIGN KEY(`customFieldID`) REFERENCES `custom_field`(`customFieldID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`assetTypeID`) REFERENCES `asset_type`(`assetTypeID`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
//                database.execSQL("CREATE TABLE IF NOT EXISTS `asset_custom_field` (`assetCustomFieldID` TEXT NOT NULL, `assetCustomFieldValue` TEXT, `assetID` TEXT, `customFieldID` TEXT, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`assetCustomFieldID`), FOREIGN KEY(`assetID`) REFERENCES `asset`(`assetID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`customFieldID`) REFERENCES `custom_field`(`customFieldID`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
//
//            }
//        }
//        val MIGRATION_4_5 = object : Migration(4, 5) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE 'asset' ADD COLUMN 'imageLocalPath' TEXT DEFAULT NULL")
//
//                //drop asset partNo manufacturer modelNo
//                database.execSQL(
//                    "CREATE TABLE IF NOT EXISTS `asset_new` (`assetID` TEXT NOT NULL, `assetTypeID` TEXT, `assetCode` TEXT, `assetDescription` TEXT, `bundleSize` INTEGER, `assetLat` REAL, `assetLong` REAL, `assetStatusID` TEXT, `assetLocationID` TEXT, `tagID` TEXT, `imageUrl` TEXT, `currentWorkFlowStepID` TEXT, `workFlowID` TEXT, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `isSynched` INTEGER, `dataVersion` REAL, PRIMARY KEY(`assetID`), FOREIGN KEY(`assetTypeID`) REFERENCES `asset_type`(`assetTypeID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`assetStatusID`) REFERENCES `asset_status`(`assetStatusID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`assetLocationID`) REFERENCES `asset_location`(`assetLocationID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`tagID`) REFERENCES `tag`(`tagID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`workFlowID`) REFERENCES `workflow`(`workFlowID`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`currentWorkFlowStepID`) REFERENCES `workflow_step`(`workFlowStepID`) ON UPDATE NO ACTION ON DELETE NO ACTION )"
//                )
//                database.execSQL("INSERT INTO`asset_new` (`assetID`, `assetTypeID` , `assetCode`, `assetDescription` , `bundleSize` , `assetLat` , `assetLong` , `assetStatusID` , `assetLocationID` , `tagID` , `imageUrl` , `creationDate` , `modificationDate` , `createdBy` , `modifiedBy` , `associatedCompanyID` , `isDeleted` , `isSynched` , `dataVersion` )SELECT `assetID`, `assetTypeID` , `assetCode`, `assetDescription` , `bundleSize` , `assetLat` , `assetLong` , `assetStatusID` , `assetLocationID` , `tagID` , `imageUrl` , `creationDate` , `modificationDate` , `createdBy` , `modifiedBy` , `associatedCompanyID` , `isDeleted` , `isSynched` , `dataVersion` FROM  `asset`")
//                database.execSQL("DROP TABLE asset")
//                database.execSQL("ALTER TABLE asset_new RENAME TO asset")
//            }
//        }
//
//        val MIGRATION_5_6 = object : Migration(5, 6) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL(
//                    "CREATE TABLE IF NOT EXISTS `user_rights` (`roleRightID` TEXT NOT NULL, `roleID` TEXT, `rightID` TEXT, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`roleRightID`))"
//                )
//            }
//        }
//        val MIGRATION_6_7 = object : Migration(6, 7) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE 'workflow_step_action' ADD COLUMN 'assetLocationActionID' TEXT DEFAULT NULL")
//                database.execSQL("CREATE TABLE IF NOT EXISTS `project` (`projectID` TEXT NOT NULL, `projectCode` TEXT, `projectName` TEXT, `assetLocationID` TEXT, `projectStatusID` TEXT, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`projectID`), FOREIGN KEY(`assetLocationID`) REFERENCES `asset_location`(`assetLocationID`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
//            }
//        }
//        val MIGRATION_7_8 = object : Migration(7, 8) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("CREATE TABLE IF NOT EXISTS `asset_attachment` (`assetAttachmentID` TEXT NOT NULL, `assetAttachmentName` TEXT, `assetAttachmentUrl` TEXT, `assetAttachmentLocalPath` TEXT, `assetID` TEXT, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`assetAttachmentID`), FOREIGN KEY(`assetID`) REFERENCES `asset`(`assetID`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
//            }
//        }
//        val MIGRATION_8_9 = object : Migration(8, 9) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE 'asset' ADD COLUMN 'projectID' TEXT DEFAULT NULL")
//            }
//        }
//        val MIGRATION_9_10 = object : Migration(9, 10) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE 'asset' ADD COLUMN 'parentID' TEXT DEFAULT NULL")
//            }
//        }
//        val MIGRATION_10_11 = object : Migration(10, 11) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE 'workflow_step_action' ADD COLUMN 'isAttachImageEnabled' INTEGER")
//            }
//        }
//        val MIGRATION_11_12 = object : Migration(11, 12) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("CREATE TABLE IF NOT EXISTS `company` (`companyID` TEXT NOT NULL, `companyName` TEXT, `hasSync` INTEGER, `isActive` INTEGER, `hasRent` INTEGER, `isCurrent` INTEGER, `creationDate` TEXT, `modificationDate` TEXT, `createdBy` TEXT, `modifiedBy` TEXT, `associatedCompanyID` TEXT, `isDeleted` INTEGER, `dataVersion` REAL, PRIMARY KEY(`companyID`))")
//            }
//        }
//        val MIGRATION_12_13 = object : Migration(12, 13) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE 'asset' ADD COLUMN 'oldWorkFlowID' TEXT DEFAULT NULL")
//                database.execSQL("ALTER TABLE 'workflow' ADD COLUMN 'releaseStepID' TEXT DEFAULT NULL")
//            }
//        }
        val builder = Room.databaseBuilder(app, com.vertie.data.Database::class.java, Constants.DATABASE_NAME)
//            .addMigrations(
////                MIGRATION_1_2,
////                MIGRATION_2_3,
////                MIGRATION_3_4,
////                MIGRATION_4_5,
////                MIGRATION_5_6,
////                MIGRATION_6_7,
////                MIGRATION_7_8,
////                MIGRATION_8_9,
////                MIGRATION_9_10,
////                MIGRATION_10_11,
////                MIGRATION_11_12,
////                MIGRATION_12_13
//            )
            .build()
        //SeedUtils.populateInitialData(builder)
        return builder

    }

}