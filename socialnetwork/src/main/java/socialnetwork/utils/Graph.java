package socialnetwork.utils;

import java.util.LinkedList;
import java.util.List;

public class Graph {
    int size;
    LinkedList<Integer>[] adjListArray;
    int comunitati = 0;

    public Graph(int size) {
        this.size = size;
        this.adjListArray = new LinkedList[size];

        for(int i = 0; i < size; i++){
            adjListArray[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int src, int dest){
        adjListArray[src].add(dest);
        adjListArray[dest].add(src);
    }

    int DFS(int start, boolean[] visited,int ans){
        visited[start] = true;
        ans++;
        for(int nod : adjListArray[start]){
            if(!visited[nod]){
                ans = DFS(nod, visited, ans);
            }
        }
        return ans;
    }

    void DFSMax(int start, boolean[] visited,int ans, int[] comunitateMax){
        visited[start] = true;
        comunitateMax[ans]=start;
        ans++;
        for(int nod : adjListArray[start]){
            if(!visited[nod]){
                DFSMax(nod, visited, ans, comunitateMax);
            }
        }
    }


    public int componenteConexe(){
        int communitySize;
        boolean[] visited = new boolean[size];
        for(int i = 0; i < size; ++i){
            if(!visited[i]){
                communitySize = 0;
                communitySize = DFS(i, visited, communitySize);
                if(communitySize > 1)
                    comunitati++;
            }
        }
        return  comunitati;
    }


    public int[] comunitateMaxima(){
        int max = -1;
        int maxStart = -1;
        int communitySize;
        boolean[] visited = new boolean[size];
        for(int i = 0; i < size; ++i){
            if(!visited[i]){
                communitySize = 0;
                communitySize = DFS(i, visited, communitySize);
                if(communitySize > max){
                    max = communitySize;
                    maxStart = i;
                }
            }
        }

        boolean[] visitedMax = new boolean[size];
        int[] comunitateMax = new int[max];
        DFSMax(maxStart, visitedMax, 0, comunitateMax);
        return  comunitateMax;
    }
}
