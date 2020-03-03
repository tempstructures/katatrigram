package com.tt.trigramsol;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Trigram {

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public static void main(String[] args) {
		Scanner sc2 = null;
		Scanner s2 = null;
		List<List<String>> lines = new ArrayList<>();
		
		try {

			String content = readFile("//Users//taruntangar//MicroServices//AI_WS//Trigrams//resources//trial2.txt",
					StandardCharsets.UTF_8);
			String[] arrStr = content.split("\\.");
			
			for(String s: arrStr) {
//				System.out.println("Line:: "+s);
				List<String> words = Arrays.asList(s.split("\\W+"));
				lines.add(words);
				for(String str: words) {
					if(!str.isEmpty())
						System.out.println("Word: "+str);
				}
			}
			System.out.println(lines.size());
			List<TriMap> hMapList = new ArrayList<>();
			//Now Read and prepare map
			for(List<String> wordList: lines) {
				TriMap hmap = new TriMap();
				for(int i=0; i<wordList.size()-2; i++) {
					String key = wordList.get(i)+" "+wordList.get(i+1);
					String value = "";
					if(i<wordList.size()-2){
						value = wordList.get(i+2);
					}
					hmap.put(key, value);
				}
				hMapList.add(hmap);
			}
			
			
//			System.out.println(content);
//			sc2 = new Scanner(new File("//Users//taruntangar//MicroServices//AI_WS//Trigrams//resources//trial.txt"));
//
//			while (sc2.hasNextLine()) {
//				s2 = new Scanner(sc2.nextLine());
//				int counter = 0;
//
//				while (s2.hasNext()) {
//					String s = s2.next();
//
//					if (counter < 2) {
//						if (key.isEmpty()) {
//							key = s;
//						} else {
//							key = key + " " + s;
//						}
//					}
//					if (counter == 2) {
//						value = s;
//						hmap.put(key, value);
//						counter = 0;
//						key = s;
//						value = "";
//					}
//					counter++;
//				}
//				if (!key.isEmpty() && value.isEmpty()) {
//					hmap.put(key, value);
//				}
//			}
			for(TriMap t: hMapList) {
				System.out.println(":::::::Map Print:::::::::");
				t.print();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sc2 != null) {
				sc2.close();
			}
			if (s2 != null) {
				s2.close();
			}
		}
	}

}
