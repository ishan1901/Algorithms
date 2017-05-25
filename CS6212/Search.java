package com.example.Trie;

import java.util.List;

public interface Search{
	public void insert(String str, Contact contact);
	public List<Contact> search(String str);
}
