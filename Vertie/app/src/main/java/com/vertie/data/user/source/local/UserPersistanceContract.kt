package com.vertie.data.user.source.local

class UserPersistanceContract {
    class UserEntry {
        companion object {
            val USER_TOKEN = "token"
            val USER_ID = "user_id"
            val USER_NAME = "user_name"
            val USER_EMAIL = "user_email"
            val COMPANY_ID = "company_id"
            val USER_IMAGE_URL = "user_image_url"
            val LANGUAGE = "language"
            val DATA_VERSION = "data_version"
            val UPLLOAD_NOTIFICATION_COUNT = "upload_notification_count"
            val PENDING_CHANGES = "pending_changes"
            val IS_CONFIGURED = "is_configured"
            val TABLET_LOCATION_ID = "tablet_location_id"
            val IS_DATABASE_FILLED = "is_database_filled"
            val FCM_TOKEN = "fcm_token"
            val USER_TABLET_CONFIGURATION_ID = "user_tablet_configuration_id"
            val IS_CONFIRMATION_REQUIRED = "is_confirmation_required"
            val HAS_SYNC = "has_sync"
            val SKIP_NO_WORKFLOW = "skip_no_workflow"
            val USER_PASSWORD = "user_password"
        }
    }
}