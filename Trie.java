package com.example.Trie;

import java.util.HashMap;

public class Trie {
	private static class TrieNode {
		boolean endOfWord;
		HashMap<Character,TrieNode> map;
		
		public TrieNode() {
			endOfWord = false;
			map = new HashMap<>();
		}
	}
	
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	public void insert(String word) {
		insert(root, word.toCharArray());
	}
	
	private void insert(TrieNode node, char[] word) {
		for (int i = 0; i < word.length; i++) {
			if (node.map.containsKey(word[i])) {
				node = node.map.get(word[i]);
				continue;
			}
			node.map.put(word[i], new TrieNode());
			node = node.map.get(word[i]);
		}
		node.endOfWord = true;
	}
	
	public boolean search(String s) {
		 return search(root, s.toCharArray());
	}
	
	 private boolean search(TrieNode node, char[] word) {
		 for (int i = 0; i < word.length; i++) {
			 if (node.map.containsKey(word[i])) {
				 node = node.map.get(word[i]);
			 } else {
				 return false;
			 }
		 }
		return node.endOfWord;
		 
	 }
	 
	 public void searchAllWords() {
		 searchWithPrefix(root, new StringBuilder(), 0);
	 }
	 
	 private void searchWithPrefix(TrieNode node, StringBuilder s, int index) {
		 if (node.endOfWord == true) {
			 System.out.println(s);
		 } 
		 for (Character c : node.map.keySet()) {
			 s.append(c);
			 searchWithPrefix(node.map.get(c),s, index + 1);
			 s.deleteCharAt(index);
		 }
	 }
	 
	 public void searchWithPrefix(String str) {
		 TrieNode temp = root;
		 int index = 0;
		 StringBuilder prefix = new StringBuilder();
		 for (Character c : str.toCharArray()) {
			 if (!temp.map.containsKey(c)) {
				 return;
			 }
			 temp = temp.map.get(c);
			 prefix.append(c);
			 index++;
		 }
		 searchWithPrefix(temp, prefix,index);
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
