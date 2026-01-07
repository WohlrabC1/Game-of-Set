public class BoardSquare {
   private Card card;
   private int row;
   private int col;
   private boolean selected;

   public BoardSquare(Card c, int row, int col) {
      this.card = c;
      this.row = row;
      this.col = col;
      this.selected = false;
   }

   public Card getCard() {
      return this.card;
   }

   public void setCard(Card c) {
      this.card = var1;
   }

   public int getRow() {
      return this.row;
   }

   public void setRow(int row) {
      this.row = var1;
   }

   public int getColumn() {
      return this.col;
   }

   public void setCol(int col) {
      this.col = col;
   }

   public boolean getSelected() {
      return this.selected;
   }

   public void setSelected(boolean var1) {
      if (!this.selected) {
         this.selected = true;
      } else if (this.selected) {
         this.selected = false;
      }

   }

   public String toString() {
      return String.format("%s at %d, %d %b", this.getCard(), this.row, this.col, this.selected);
   }
}
