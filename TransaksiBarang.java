import java.util.Scanner;

class TransaksiBarang {
    Scanner input = new Scanner(System.in);
    Barang25[] barang;
    Barang25[] pembelians;
    int[] jumlahPembelian;

    public TransaksiBarang(Barang25[] barang) {
        this.barang = barang;
        this.pembelians = new Barang25[barang.length];
        this.jumlahPembelian = new int[barang.length];
    }

    public void tampilkanBarang() {
        System.out.println("\n=====================================================");
        System.out.println("|                   Daftar Barang                   |");
        System.out.println("=====================================================");
        System.out.printf("| %-10s | %-15s | %-10s | %-5s |\n", "Kode", "Nama", "Harga", "Stok");
        System.out.println("=====================================================");
        boolean isAda = false;
        for (Barang25 brg : barang) {
            if (brg != null) {
                System.out.printf("| %-10s | %-15s | %-10.2f | %-5d |\n", brg.kode, brg.nama, brg.harga, brg.stok);
                isAda = true;
            }
        }
        if (!isAda) {
            System.out.println("|            Maaf, data barang kosong!             |");
        }
        System.out.println("=====================================================");
    }

    public void tampilkanBarangPembelian() {
        System.out.println("\n====================================================================");
        System.out.println("|                           List Belanjaan                         |");
        System.out.println("====================================================================");
        System.out.printf("| %-10s | %-15s | %10s | %5s | %12s |\n", "Kode", "Nama", "Harga", "Qty", "Total");
        System.out.println("====================================================================");
        boolean isAdaPembelian = false;
        double totalHargaSemua = 0;
        for (int i = 0; i < pembelians.length; i++) {
            if (pembelians[i] != null) {
                double totalHarga = pembelians[i].harga * jumlahPembelian[i];
                System.out.printf("| %-10s | %-15s | %,10.2f | %5d | %,12.2f |\n", pembelians[i].kode, pembelians[i].nama, pembelians[i].harga, jumlahPembelian[i], totalHarga);
                totalHargaSemua += totalHarga;
                isAdaPembelian = true;
            }
        }
        if (!isAdaPembelian) {
            System.out.println("|                    Belum ada barang dibeli                 |");
        } else {
            System.out.println("====================================================================");
            System.out.printf("| %-35s | %,12.2f |\n", "Total Belanja:", totalHargaSemua);
        }
        System.out.println("====================================================================");
    }

    public void melakukanPembelian() {
        tampilkanBarang();
        while (true) {
            System.out.print("\nMasukkan kode barang: ");
            String kodeBarang = input.nextLine();
            boolean barangDitemukan = false;
            for (int i = 0; i < barang.length; i++) {
                if (barang[i] != null && barang[i].kode.equals(kodeBarang)) {
                    barangDitemukan = true;
                    if (barang[i].stok > 0) {
                        System.out.print("Masukkan jumlah yang ingin dibeli: ");
                        int jumlah = input.nextInt();
                        input.nextLine();
                        if (jumlah > 0 && jumlah <= barang[i].stok) {
                            barang[i].stok -= jumlah;
                            boolean sudahDibeli = false;
                            for (int j = 0; j < pembelians.length; j++) {
                                if (pembelians[j] != null && pembelians[j].kode.equals(kodeBarang)) {
                                    jumlahPembelian[j] += jumlah;
                                    sudahDibeli = true;
                                    break;
                                }
                            }
                            if (!sudahDibeli) {
                                for (int j = 0; j < pembelians.length; j++) {
                                    if (pembelians[j] == null) {
                                        pembelians[j] = barang[i];
                                        jumlahPembelian[j] = jumlah;
                                        break;
                                    }
                                }
                            }
                            System.out.println("Pembelian berhasil: " + barang[i].nama + " sebanyak " + jumlah);
                        } else {
                            System.out.println("Jumlah tidak valid atau stok tidak mencukupi.");
                        }
                    } else {
                        System.out.println("Barang " + kodeBarang + " stok habis.");
                    }
                }
            }
            if (!barangDitemukan) {
                System.out.println("Barang " + kodeBarang + " tidak ditemukan.");
            }
            System.out.print("Apakah akan belanja kembali (Y/N)? : ");
            String lanjut = input.nextLine();
            if (lanjut.equalsIgnoreCase("N")) {
                break;
            }
        }
    }
}