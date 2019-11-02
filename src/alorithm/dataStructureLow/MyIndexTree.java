package alorithm.dataStructureLow;

class MyIndexTree {
    // 구간합을 저장해 놓는 IndexTree
    // sumInterVal(startIndex , endIndex)
    
   private int tree[];
   private int originalStart;
   
   public MyIndexTree( int[] orignal ) {
       int originalSize = orignal.length;
       originalStart = 1;
       
       while (originalStart < originalSize)
           originalStart <<= 1;
       
       tree = new int[originalStart*2];
       
       for( int index = 0; index < orignal.length; index++ ) {
           update( index, orignal[index] );
       }
   }
   
   public void update( int index, int value) {
       index += originalStart;
       int beforeValue = tree[index];
       while(index > 0) {
           tree[index] = tree[index] - beforeValue + value;
           index>>=1;
       }
   }

  public int sumInterVal( int start, int end ) {
      start += originalStart;
      end += originalStart;
      int sum = 0;
      while ( start < end ) {
          if( start%2 == 1) {
              sum += tree[start]; 
              start++;
          }
          if( end%2 == 0) {
              sum += tree[end];
              end--;
          }
          start>>=1;
          end>>=1;
      }
      if( start == end ) {
          // 같은 노드내 탐색일 경우 합치기
          sum += tree[start];
      }
      
      return sum;
  }
  public int sumTotal( int end ) {
      int start = 0;
      start += originalStart;
      end += originalStart;
      int sum = 0;
      while ( start < end ) {
          if( start%2 == 1) {
              sum += tree[start]; 
              start++;
          }
          if( end%2 == 0) {
              sum += tree[end];
              end--;
          }
          start>>=1;
          end>>=1;
      }
      if( start == end ) {
          sum += tree[start];
      }
      
      return sum;
  }
   public void printOriginal() {
       for (int index = 0; index < tree.length; index++) {
           System.out.print(tree[index] + " ");
       }
       System.out.println();
   }
   
   public int search( int findNumber ) {
       int findIndex = 1;
       int leftChild;
       while(findIndex < originalStart) {
           
           leftChild = tree[findIndex*2]; 
           
           
           if(findNumber <= leftChild) {
               findIndex = findIndex * 2; // 자식 노드의 왼쪽 값
           } else {
               findNumber = findNumber - leftChild;
               findIndex = findIndex * 2 + 1; 
           }
       }

       return findIndex - originalStart;
   }
   
   public int search( int rootIndex, int findNumber ) {
       if( rootIndex >= originalStart ) return rootIndex - originalStart;
       
       int leftChild =  tree[rootIndex*2];
       if( findNumber <=leftChild  ) {
           return search( rootIndex*2, findNumber );
       } else {
           return search( rootIndex*2+1, findNumber - leftChild );
       }
   }
   
   public int getTreeData ( int index ) {
       return tree[originalStart + index];
   }
}