
public class QuickUWPC implements IUnionFind{
	private int[] parent; 
	private int[] size; 
	private int count; 
	public QuickUWPC() {
		parent = null; 
		size = null; 
		count = 0; 
	}
	public QuickUWPC(int n) {
		initialize(n); 
	}
	public void initialize(int n) {
		parent = new int[n]; 
		size = new int[n]; 
		count = n; 
		for (int i=0; i<n; i++) {
			parent[i] = i; 
			size[i] = 1; 
		}
	}
	public int components() {
		return count; 
	}
	public int find(int x) {
		while (x != parent[x])
            x = parent[x];
        return x;
	}
	public boolean connected(int p, int q) {
		return find(p) == find(q); 
	}
	public void union(int p, int q) {
		if (find(p) == find(q))
			return; 
		if (size[find(p)] < size[find(q)]) {
			parent[find(p)] = find(q); 
			size[find(q)] += size[find(p)];
		}
		else {
			parent[find(q)] = find(p); 
			size[find(p)] += size[find(q)];		
		}
		count = count - 1; 
	}
}
