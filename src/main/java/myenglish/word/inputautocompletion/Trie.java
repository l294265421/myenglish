package myenglish.word.inputautocompletion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.IOUtils;

/**
 * 前缀树
 * @author liyuncong
 *
 */
public class Trie {
	private final TrieNode ROOT_NODE = new TrieNode('/');

	public boolean contains(String item) {
		// 去掉首尾空白字符
		item = item.trim();
		int len = item.length();
		if (len < 1) {
			return false;
		}
		// 从根节点开始查找
		TrieNode node = ROOT_NODE;
		for (int i = 0; i < len; i++) {
			char character = item.charAt(i);
			TrieNode child = node.getChild(character);
			if (child == null) {
				// 未找到匹配节点
				return false;
			} else {
				// 找到节点，继续往下找
				node = child;
			}
		}
		if (node.isTerminal()) {
			return true;
		}
		return false;
	}

	public void addAll(List<String> items) {
		for (String item : items) {
			add(item);
		}
	}

	public void add(String item) {
		// 去掉首尾空白字符
		item = item.trim();
		int len = item.length();
		if (len < 1) {
			// 长度小于1则忽略
			return;
		}
		// 从根节点开始添加
		TrieNode node = ROOT_NODE;
		for (int i = 0; i < len; i++) {
			char character = item.charAt(i);
			TrieNode child = node.getChildIfNotExistThenCreate(character);
			// 改变顶级节点
			node = child;
		}
		// 设置终结字符，表示从根节点遍历到此是一个合法的词
		node.setTerminal(true);
	}
	
	/**
	 * 获得前缀为suffix的所有词。
	 * @param suffix 前缀
	 * @return
	 */
	public List<String> getWordsBySuffix(String suffix) {
		List<String> words = new LinkedList<String>();
		suffix = suffix.trim();
		int len = suffix.length();
		if (len < 1) {
			return words;
		}
		
		TrieNode cursor = ROOT_NODE;
		// 找到最后一个字符对应的节点
		for (int i = 0; i < len; i++) {
			char character = suffix.charAt(i);
			TrieNode child = cursor.getChild(character);
			if (child == null) {
				// 未找到匹配节点
				return words;
			} else {
				// 找到节点，继续往下找
				cursor = child;
			}
		}
		
		if (cursor.isTerminal()) {
			words.add(suffix);
		}
		
		for(TrieNode child : cursor.getChildren()) {
			words.addAll(LevelTraverse(child, suffix));
		}
		
		return words;
	}
	
	/**
	 * 层次遍历树，获得前缀suffix与前缀树node组合成的所有单词
	 * @param node 子树根节点
	 * @param suffix 整棵树的前缀，包括根节点
	 * @return
	 */
	private List<String> LevelTraverse(TrieNode node, String suffix) {
		List<String> words = new LinkedList<String>();
		LinkedList<TrieNode> queue = new LinkedList<Trie.TrieNode>();
		// 与queue对应，存储的是queue中非哨兵结点的对应的前缀
		LinkedList<String> suffixQueue = new LinkedList<String>();
		queue.add(node);
		suffixQueue.addLast(suffix);
		queue.add(TrieNode.FLAG_NODE);
		while (!queue.isEmpty()) {
			TrieNode temp = queue.poll();
			
			// 上一层元素已遍历完
			if (temp == TrieNode.FLAG_NODE) {
				if (!queue.isEmpty()) {
					queue.add(TrieNode.FLAG_NODE);
				}
				continue;
			}
			suffix = suffixQueue.poll();
			
			if (temp.isTerminal()) {
				words.add(suffix + temp.character);
			}
			
			Collection<TrieNode> children = temp.getChildren();
			for (TrieNode child : children) {
				queue.add(child);
				suffixQueue.addLast(suffix + temp.getCharacter());
			}
		}
		return words;
	}

	private static class TrieNode {
		private char character;
		private boolean terminal;
		private final Map<Character, TrieNode> children = new 
				ConcurrentHashMap<>();
		
		public static final TrieNode FLAG_NODE = new TrieNode();
		
		public TrieNode() {
		}

		public TrieNode(char character) {
			this.character = character;
		}

		public boolean isTerminal() {
			return terminal;
		}

		public void setTerminal(boolean terminal) {
			this.terminal = terminal;
		}

		public char getCharacter() {
			return character;
		}

		public void setCharacter(char character) {
			this.character = character;
		}

		public Collection<TrieNode> getChildren() {
			return this.children.values();
		}

		public TrieNode getChild(char character) {
			return this.children.get(character);
		}

		public TrieNode getChildIfNotExistThenCreate(char character) {
			TrieNode child = getChild(character);
			if (child == null) {
				child = new TrieNode(character);
				addChild(child);
			}
			return child;
		}

		public void addChild(TrieNode child) {
			this.children.put(child.getCharacter(), child);
		}

		public void removeChild(TrieNode child) {
			this.children.remove(child.getCharacter());
		}

		@Override
		public String toString() {
			return "TrieNode [character=" + character + ", terminal="
					+ terminal + ", children=" + children + "]";
		}
		
	}

	public static void main(String[] args) throws IOException {
		InputStream input = new FileInputStream("d:/test/words.txt");
		List<String> words = IOUtils.readLines(input, "utf-8");
		Trie trie = new Trie();
		trie.addAll(words);
		List<String> result = trie.getWordsBySuffix("like");
		for (String string : result) {
			System.out.println(string);
		}
	}
}
