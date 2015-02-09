public class Percolation {
    
    private int gridSize;
    private WeightedQuickUnionUF linearGrid;
    private boolean grid[][];
    
   public Percolation(int N)               // create N-by-N grid, with all sites blocked
   {
       this.gridSize = N;
       linearGrid = new WeightedQuickUnionUF(gridSize*gridSize+2);
       grid = new boolean[gridSize][gridSize];
       
   }
   public void open(int i, int j)          // open site (row i, column j) if it is not open already
   {
       validate(i,j);
       if(!isOpen(i,j)){
           grid[i-1][j-1] = true;
           unite(i,j,i+1,j);
           unite(i,j,i-1,j);
           unite(i,j,i,j+1);
           unite(i,j,i,j-1);
           
           if (i == 1) { // connect to virtual top site
               linearGrid.union(0, xyTo1D(i, j));
           } 
           if (i == gridSize) { // connect to virtual bottom site
               linearGrid.union(1, xyTo1D(i, j));
           }
       }
   }
   public boolean isOpen(int i, int j){
       validate(i, j);
       return grid[i - 1][j - 1];
   }     // is site (row i, column j) open?
   public boolean isFull(int i, int j){
       validate(i, j);
       return linearGrid.connected(0, xyTo1D(i, j));
   }     // is site (row i, column j) full?
   public boolean percolates(){
       return linearGrid.connected(0, 1);
   }             // does the system percolate?
       
   private int xyTo1D(int i, int j){
       validate(i,j);
       return (i - 1) * gridSize + j+1;
   }
   
//   public static void main(String[] args)   // test client (optional)
//   {
//       Percolation p = new Percolation(2);
//       System.out.println(p.grid[0][0]);
//   }
   
   private void unite(int i, int j, int m, int n) { // 1-based coordinates
       if (m > 0 && n > 0 && m <= gridSize && n <= gridSize && isOpen(m, n)) {
           linearGrid.union(xyTo1D(i, j), xyTo1D(m, n));
       }
//       else if (m == 1) { // connect to virtual top site
//           linearGrid.union(0, xyTo1D(i, j));
//           
//       } 
//       else if (m == gridSize) { // connect to virtual bottom site
//           linearGrid.union(1, xyTo1D(i, j));
//       }
   }
   
   private void validate(int i, int j) {
       int x = i - 1;
       int y = j - 1;
       if (x < 0 || y < 0 || x >= gridSize || y >= gridSize) {
           throw new IndexOutOfBoundsException(String.format("Cell at Row i=%s and Column j=%s is out of bounds!", i, j));
       }
 }
}