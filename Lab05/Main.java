import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /***
         * The code below can be edited.  It is simply here to support your own testing.
         */
        int[][] Example1 = {{0, 1}, {1, 2}, {1, 3}, {1, 4}, {4, 5}, {3, 5}, {5, 6}};
        int[] sol1 = findCutEdge(Example1);
        System.out.println("The first solution is");
        System.out.println(Arrays.toString(sol1));

        int[][] Example2 = {{0, 1}, {1, 2}, {1, 3}, {2, 3}, {4, 5}, {3, 5}, {5, 6}};
        int[] sol2 = findCutEdge(Example2);
        System.out.println("The second solution is");
        System.out.println(Arrays.toString(sol2));

        int[][] Example3 = {{0, 1}, {1, 2}, {1, 3}, {2, 6}, {4, 5}, {3, 5}, {5, 6}};
        int[] sol3 = findCutEdge(Example3);
        System.out.println("The third solution is");
        System.out.println(Arrays.toString(sol3));
    }

    public static int[] findCutEdge(int[][] edges) {
        /***
         * TODO: Fix this method
         * Right now this method simply returns the same integer pair every time
         * You should return an edge such that, once removed from the graph,
         * a tree remains.
         */
        DisjointSets disjointSets = new DisjointSets(edges.length + 1); // Create DisjointSets instance

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // Perform union operation
            if (disjointSets.Find(u) != disjointSets.Find(v)) {
                disjointSets.Union(u, v);
            } else {
                // Return the edge if removing it forms a tree
                return edge;
            }
        }

        return new int[]{-1, -1}; // Return an invalid edge if no such edge is found
    }

    private static class DisjointSets {

        public int n;
        private int[] setArr;

        /***
         * Constructor (required to create disjoint sets)
         * @param numElts initially sets are singletons: {0}, {1}, . . . {numElts-1}
         */
        public DisjointSets(int numElts) {
            n = numElts;
            int[] tempArr = new int[n];
            for (int i = 0; i < n; i++) {
                tempArr[i] = -1;
            }
            setArr = tempArr;
        }

        public int Find(int k) {
            if (setArr[k] >= 0) {
                setArr[k] = Find(setArr[k]);
                return setArr[k];
            } else {
                return k;
            }
        }

        private int getRank(int k) {
            return setArr[Find(k)];
        }

        public void Union(int a, int b) {
            int x = Find(a);
            int y = Find(b);

            if (x == y) {
                return;
            }

            if (getRank(x) > getRank(y)) {
                setArr[x] = y;
            } else if (getRank(x) < getRank(y)) {
                setArr[y] = x;
            } else {
                setArr[x] = y;
                setArr[Find(y)] = setArr[Find(y)] - 1;
            }
        }

        public void printSets() {
            System.out.println(Arrays.toString(setArr));
        }
    }
}
