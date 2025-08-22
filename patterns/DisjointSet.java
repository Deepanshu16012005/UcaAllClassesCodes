class DisjointSet {
  int [] rank;
  int [] size;
  int [] parent;
// no of nodes or elements
  DisjointSet(int n){
    //1 based indexing
    rank = new int [n+1];
    parent = new int [n+1];
    for(int i=1;i<parent.length;i++){
      parent[i]=i;
    }
    size = new int [n+1];
    for(int ele:size){
      ele=1;
    }
  }

  public int findUltParent(int node){
    if (node == parent[node]){
      return node;
    }
    //path compression 
    return parent[node]=findUltParent(parent[node]);
  }
	
  public void unionByRank (int a , int b){
    //join two nodes 
    int ua=findUltParent(a);
    int ub=findUltParent(b);
    if(ua==ub) return;

    if(rank[ua]<rank[ub]){
      //join a with b
      //change ultimate parent value of a to b
      parent[ua]=ub; 
    }else if(rank[ua]>rank[ub]){
      parent[ub]=ua;
    }else{
      parent[ua]=ub;
      // equal case we also increase the rank
      // ub is parent of ua 
      rank[ub]++;
    }
  }

  public void unionBySize (int a , int b){
    //join two nodes
    int ua=findUltParent(a);
    int ub=findUltParent(b);
    if(ua==ub) return;

    if(size[ua]<size[ub]){
      //join a with b
      //change ultimate parent value of a to b
      parent[ua]=ub;
      // ub is parent
      size[ub]+=size[ua];
    }else if(rank[ua]>rank[ub]){
      parent[ub]=ua;
      size[ua]+=size[ub];
    }else{
      parent[ua]=ub;
      // equal case we also increase the rank
      // ub is parent of ua
      size[ub]+=size[ua];
    }
  }
}
