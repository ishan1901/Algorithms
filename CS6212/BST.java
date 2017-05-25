package com.example.Trie;

import java.util.ArrayList;
import java.util.List;

public class BST implements Search{
	private static class TreeNode {
		String string;
		TreeNode left;
		TreeNode right;
		Contact contact;
		public TreeNode(String string, Contact c) {
			this.string = string;
			this.left = null;
			this.right = null;
			contact = c;
		}
	}
	
	private TreeNode root;
	
	public void insert(String str, Contact contact) {
		root = insert(str, root, contact);
	}
	
	private TreeNode insert(String str, TreeNode root, Contact contact) {
		if (root == null) {
			root =  new TreeNode(str, contact);
			return root;
		} 
		if (str.compareTo(root.string) <= 0) {
			root.left = insert(str, root.left, contact);
		} else {
			root.right = insert (str, root.right, contact);
		}
		return root;
	}
	
	public void inorder() {
		inorder(root);
	}
	
	private void inorder(TreeNode root) {
		if (root == null) {
			return;
		}
		inorder(root.left);
		System.out.println(root.string);
		inorder(root.right);
	}
	
	
	public List<Contact> search(String str) {
		List<Contact> contacts = new ArrayList<>();
		search(str, root, contacts);
		return contacts;
	}
	
	private void search(String str, TreeNode root, List<Contact> contacts) {
		if (root == null) {
			return;
		}
		if (root.string.startsWith(str)) {
			contacts.add(root.contact);
		}
		if (str.compareTo(root.string) <= 0) {
			search(str, root.left, contacts);
		} else {
			search(str, root.right, contacts);
		}
	}
	
}
