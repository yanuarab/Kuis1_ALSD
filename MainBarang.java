import java.util.Scanner;

public class MainBarang {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Barang25[] dataBarang = new Barang25[5];
        dataBarang[0] = new Barang25("B01", "Sabun", 5000, 10);
        dataBarang[1] = new Barang25("B02", "Pasta Gigi", 8000, 5);
        dataBarang[2] = new Barang25("B03", "Biore", 3000, 8);
        dataBarang[3] = new Barang25("B04", "Shampoo", 1000, 12);
        dataBarang[4] = new Barang25("B05", "Sikat Gigi", 5000, 20);

        TransaksiBarang transaksi = new TransaksiBarang(dataBarang);

        int pilihanMenu;
        do {
            System.out.println("====================");
            System.out.println("TOKO BUTO ABADI");
            System.out.println("====================");
            System.out.println("1. Tampilkan Barang");
            System.out.println("2. Lakukan Pembelian");
            System.out.println("3. Tampilkan Daftar Pembelian");
            System.out.println("4. Keluar");
            System.out.print("Pilihan: ");
            pilihanMenu = input.nextInt();
            input.nextLine(); 

            switch (pilihanMenu) {
                case 1:
                    transaksi.tampilkanBarang();
                    break;
                case 2:
                    transaksi.melakukanPembelian();
                    break;
                case 3:
                    transaksi.tampilkanBarangPembelian();
                    break;
                case 4:
                    System.out.println("Terima Kasih Telah Berbelanja Di Toko Kami!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihanMenu != 4);

        input.close();
    }
}
