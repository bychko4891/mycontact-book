package com.example.mycontactbook;

import java.util.Arrays;

public class Contact {
    private  int EMAIL_ARRAY_LENGTH = 3, SOCIAL_ARRAY_LENGTH = 5;
    private String contactName;
    private ContactInfo phoneContactInfo;
    private NameContactInfo nameContactInfo;
    private Email[] contactEmailsArrays; // = new Email[EMAIL_ARRAY_LENGTH];
    private Social[] contactSocialArrays;// = new Social[SOCIAL_ARRAY_LENGTH];

    public Contact(String contactName) {
        if (contactName == null || contactName.isBlank()) {
            throw new IllegalArgumentException("name is NULL or the wrong");
        } else {
            this.contactName = contactName;
            this.nameContactInfo = new NameContactInfo(contactName);
            this.contactEmailsArrays = new Email[EMAIL_ARRAY_LENGTH];
            this.contactSocialArrays = new Social[SOCIAL_ARRAY_LENGTH];
        }
    }

    public void rename(String newName) {
        if (newName != null) {
            if (!newName.isBlank()) {
                contactName = newName;
            }
        }
    }

    public Email addEmail(String localPart, String domain) {
        Email email = null;
        if (limit(contactEmailsArrays)) {
            int count = count(contactEmailsArrays);
            email = new Email(localPart + "@" + domain);
            contactEmailsArrays[count] = email;
        }
        return email;
    }

    private boolean limit(ContactInfo[] arrLimit) {
        boolean limit = false;
        for (ContactInfo arr : arrLimit) {
            if (arr == null) {
                limit = true;
                break;
            }
        }
        return limit;
    }
    private int count(ContactInfo[] arrLimit){
        int count = 0;
        while (arrLimit[count] != null) {
            count++;
            while (count == arrLimit.length) {
                count = 0;
            }
        }
        return count;
    }
    public Email addEpamEmail(String firstname, String lastname) {
        Email emailEpam = null;
        if (limit(contactEmailsArrays)) {
            emailEpam = new Email(firstname + "_" + lastname + "@" + "epam.com"){
                @Override
                public String getTitle() {
                    return "Epam Email";
                }
            };
            int count = count(contactEmailsArrays);
            contactEmailsArrays[count] = emailEpam;
        }
        return emailEpam;
    }


    public ContactInfo addPhoneNumber(int code, String number) { //******** phone ******
        ContactInfo phoneInfo = new ContactInfo() {
            @Override
            public String getTitle() {
                return "Tel";
            }

            @Override
            public String getValue() {
                String phone = "+" + code + " " + number;
                return phone;
            }
        };
        if (phoneContactInfo == null) {
            phoneInfo.getValue();
            phoneContactInfo = new Social("Tel", phoneInfo.getValue());
        } else phoneInfo = null;
        return phoneInfo;
    }


    public Social addTwitter(String twitterId) {
        Social socialId = null;
        if (limit(contactSocialArrays)) {
            int count = count(contactSocialArrays);
            socialId = new Social("Twitter", twitterId);
            contactSocialArrays[count] = socialId;
        }
        return socialId;
    }

    public Social addInstagram(String instagramId) {
        Social socialId = null;
        if (limit(contactSocialArrays)) {
            int count = count(contactSocialArrays);
            socialId = new Social("Instagram", instagramId);
            contactSocialArrays[count] = socialId;
        }
        return socialId;
    }

    public Social addSocialMedia(String title, String id) {
        Social socialId = null;
        if (limit(contactSocialArrays)) {
            int count = count(contactSocialArrays);
            socialId = new Social(title, id);
            contactSocialArrays[count] = socialId;
        }
        return socialId;
    }

    public ContactInfo[] getInfo() {
        ContactInfo[] info = new ContactInfo[1];
        info[0] = nameContactInfo;
        if (phoneContactInfo != null) {
            info = Arrays.copyOfRange(info, 0, info.length + 1);
            info[info.length - 1] = phoneContactInfo;
        }
        for (Email arr : contactEmailsArrays) {
            if (arr != null) {
                info = Arrays.copyOfRange(info, 0, info.length + 1);
                info[info.length - 1] = arr;
            }
        }
        for (Social arr : contactSocialArrays) {
            if (arr != null) {
                info = Arrays.copyOfRange(info, 0, info.length + 1);
                info[info.length - 1] = arr;
            }
        }
        return info;
    }

    private class NameContactInfo implements ContactInfo {
        public NameContactInfo(String name) {
            contactName = name;
        }
        @Override
        public String getTitle() {
            return "Name";
        }

        @Override
        public String getValue() {
            return contactName;
        }

        @Override
        public String toString() {
            return getTitle() + ": " + getValue();
        }
    }

    public static class Email implements ContactInfo {
        private String email;

        public Email() {
        }

        public Email( String email) {
            this.email = email;
        }
        @Override
        public String getTitle() {
            return "Email";
        }

        @Override
        public String getValue() {
            return email;
        }

        @Override
        public String toString() {
            return getTitle() + ": " + email;
        }
    }

    public static class Social implements ContactInfo {
        private String title;
        private String socialId;

        public Social(String title, String socialId) {
            this.socialId = socialId;
            this.title = title;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getValue() {
            return socialId;
        }
    }
}
