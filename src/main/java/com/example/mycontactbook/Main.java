package com.example.mycontactbook;

public class Main {
    public static void main(String[] args) {
        Contact contact = new Contact("William the Conqueror");

        contact.addEmail("william", "normandy.fr");
        contact.addEmail("william", "england.travel");

        contact.addEpamEmail("william", "conqueror");

        contact.addTwitter("@william1066");
        contact.addInstagram("@bayeux_tapestry");
        contact.addSocialMedia("Facebook", "la_manche_tours");

        contact.addPhoneNumber(44, "20-1066-1087");

        ContactInfo[] info = contact.getInfo();
    }
}
