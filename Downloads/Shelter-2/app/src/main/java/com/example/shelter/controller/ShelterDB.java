package com.example.shelter.controller;
import com.example.shelter.model.Shelter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ShelterDB {
	public static void saveInfo (ArrayList<Shelter> shelters) {
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("shelter.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(shelters);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in shelter.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	public static ArrayList<Shelter> Readinfo () {
		ArrayList<Shelter> shelters = null;
	
		try {
	         FileInputStream fileIn = new FileInputStream("shelter.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         shelters = (ArrayList<Shelter>) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      } catch (ClassNotFoundException c) {
	         System.out.println("Class not found");
	         c.printStackTrace();
	       
	      }
		return shelters;
	}
	public static void saveShelterDAta (HashMap<String, Shelter> shelters) {
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("shelter.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         System.out.println("searializing data");
	         out.writeObject(shelters);
	         System.out.println("searialzed data");
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in shelter.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	public static HashMap<String, Shelter> ReadShelterData () {
		HashMap<String, Shelter> shelters = null;
	
		try {
	         FileInputStream fileIn = new FileInputStream("shelter.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	       System.out.println("desearializing data");
	         shelters = (HashMap<String, Shelter>) in.readObject();
	         System.out.println("desearialized data");
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      } catch (ClassNotFoundException c) {
	         System.out.println("Class not found");
	         c.printStackTrace();
	       
	      }
		return shelters;
	}

}