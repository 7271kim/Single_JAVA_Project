package alorithm.dataStructureLow;

class BinaryIndexTree{
    // Fenwick
    
    int tree [];
    int orignalSize;
    int treeLength;
    
    public BinaryIndexTree( int[] orignal ) {
        orignalSize = orignal.length;
        tree = new int[orignalSize*2+1];
        treeLength = tree.length-orignalSize;
        for (int index = 0; index < orignal.length; index++) {
            update(index, orignal[index]);
        }
    }

    public void update(int index, int value) {
        index += 1; 
        int orignalPosision = orignalSize+index;
        int beforeValue     = tree[orignalPosision];
        tree[orignalPosision]   = value;
        while (index < treeLength) {
            tree[index] = tree[index] - beforeValue + value;
            index += (index & -index); 
        }
    }
    public int sumTotal(int index) {
        int res = 0;
        index++;
        while (index > 0) {
            res += tree[index];
            index &= index-1;
        }
        return res;
    }
    
    public int sumInterVal(int start , int end) {
        return sumTotal(end) - sumTotal(start-1);
    }
    
    public void printTotal() {
        for (int index = 0; index < tree.length; index++) {
            System.out.print(tree[index] + " ");
        }
        System.out.println();
    }
    public void printFenwick() {
        for (int index = 0; index < treeLength; index++) {
            System.out.print(tree[index] + " ");
        }
        System.out.println();
    }
    public void printOriginal() {
        for (int index = treeLength; index < tree.length; index++) {
            System.out.print(tree[index] + " ");
        }
        System.out.println();
    }
}
