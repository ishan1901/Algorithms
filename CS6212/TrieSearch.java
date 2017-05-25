package com.example.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrieSearch implements Search {
	private static class TrieNode {
		boolean endOfWord;
		HashMap<Character,TrieNode> map;
		List<Contact> contacts = new ArrayList<>();
		public TrieNode() {
			endOfWord = false;
			map = new HashMap<>();
		}
	}
	
	private TrieNode root;
	
	public TrieSearch() {
		root = new TrieNode();
	}
	
	public void insert(String word, Contact c) {
		insert(root, word.toCharArray(), c);
	}
	
	private void insert(TrieNode node, char[] word, Contact c) {
		for (int i = 0; i < word.length; i++) {
			if (node.map.containsKey(word[i])) {
				node = node.map.get(word[i]);
				continue;
			}
			node.map.put(word[i], new TrieNode());
			node = node.map.get(word[i]);
		}
		node.contacts.add(c);
		node.endOfWord = true;
	}
	 
	 public List<Contact> searchAllWords() {
		 List<Contact> listOfContact = new ArrayList<>();
		 search(root, new StringBuilder(), 0, listOfContact);
		 return listOfContact;
	 }
	 
	 private void search(TrieNode node, StringBuilder s, int index, List<Contact> listOfContact) {
		 if (node.endOfWord == false) {
			 for (Character c : node.map.keySet()) {
				 s.append(c);
				 search(node.map.get(c),s, index + 1, listOfContact);
				 s.deleteCharAt(index);
			 }
		 }
		 for (Contact c : node.contacts) {
			 listOfContact.add(c);
		 }
	 }
	 
	 public List<Contact> search(String str) {
		 TrieNode temp = root;
		 int index = 0;
		 StringBuilder prefix = new StringBuilder();
		 List<Contact> listOfContact = new ArrayList<>();
		 for (Character c : str.toCharArray()) {
			 if (!temp.map.containsKey(c)) {
				 return new ArrayList<>();
			 }
			 temp = temp.map.get(c);
			 prefix.append(c);
			 index++;
		 }
		 search(temp, prefix,index, listOfContact);
		 return listOfContact;
	 }
	 
	 public void delete(String word) {
		 delete(root, word.toCharArray(), 0);
	 }
	 
	 private boolean delete(TrieNode node, char[] word, int index) {
		 if (index == word.length) {
			 if (!node.endOfWord) {
				return false; 
			 }
			 node.endOfWord = false;
			 return node.map.size() == 0;
		 }
		 if (node.map.containsKey(word[index])) {
			 TrieNode current = node.map.get(word[index]);
			 boolean toDelete = delete(current, word, index + 1);
			 if (toDelete) {
				 node.map.remove(word[index]);
				 return node.map.size() == 0;
			 } 
		 }
		 return false;
	 }
}
