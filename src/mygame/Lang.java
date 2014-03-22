package mygame;

public class Lang{

        public Lang() {
        }

        public Lang(String title, String adminButtonText, String mapButtonText, String listButtonText, String okButtonText, String cancelButtonText, String DescriptionButtonText, String DescriptionLine2ButtonText, String DescriptionLine3ButtonText) {
            this.title = title;
            this.adminButtonText = adminButtonText;
            this.mapButtonText = mapButtonText;
            this.listButtonText = listButtonText;
            this.okButtonText = okButtonText;
            this.cancelButtonText = cancelButtonText;
            this.DescriptionButtonText = DescriptionButtonText;
            this.DescriptionLine2ButtonText = DescriptionLine2ButtonText;
            this.DescriptionLine3ButtonText = DescriptionLine3ButtonText;
        }
        

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAdminButtonText() {
            return adminButtonText;
        }

        public void setAdminButtonText(String adminButtonText) {
            this.adminButtonText = adminButtonText;
        }

        public String getMapButtonText() {
            return mapButtonText;
        }

        public void setMapButtonText(String mapButtonText) {
            this.mapButtonText = mapButtonText;
        }

        public String getListButtonText() {
            return listButtonText;
        }

        public void setListButtonText(String listButtonText) {
            this.listButtonText = listButtonText;
        }

        public String getOkButtonText() {
            return okButtonText;
        }

        public void setOkButtonText(String okButtonText) {
            this.okButtonText = okButtonText;
        }

        public String getCancelButtonText() {
            return cancelButtonText;
        }

        public void setCancelButtonText(String cancelButtonText) {
            this.cancelButtonText = cancelButtonText;
        }

        public String getDescriptionButtonText() {
            return DescriptionButtonText;
        }

        public void setDescriptionButtonText(String DescriptionButtonText) {
            this.DescriptionButtonText = DescriptionButtonText;
        }

        public String getDescriptionLine2ButtonText() {
            return DescriptionLine2ButtonText;
        }

        public void setDescriptionLine2ButtonText(String DescriptionLine2ButtonText) {
            this.DescriptionLine2ButtonText = DescriptionLine2ButtonText;
        }

        public String getDescriptionLine3ButtonText() {
            return DescriptionLine3ButtonText;
        }

        public void setDescriptionLine3ButtonText(String DescriptionLine3ButtonText) {
            this.DescriptionLine3ButtonText = DescriptionLine3ButtonText;
        }
        private String title;
        private String adminButtonText;
        private String mapButtonText;
        private String listButtonText;
        private String okButtonText;
        private String cancelButtonText;
        private String DescriptionButtonText;
        private String DescriptionLine2ButtonText;
        private String DescriptionLine3ButtonText;
        
    }