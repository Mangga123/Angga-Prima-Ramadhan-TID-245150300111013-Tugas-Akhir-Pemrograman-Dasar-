import javax.swing.JOptionPane;

public class PROJEKPERHITUNGANKAS {
    private static String[] ns;
    private static int[] ks;
    private static int jml;
    private static int saldoKas = 0;

    public static void main(String[] args) {
        inputJml();
        inputNs();
        menu();
    }

    private static void inputJml() {
        String input = JOptionPane.showInputDialog("Masukkan jumlah siswa di kelas:");
        jml = Integer.parseInt(input);
        ns = new String[jml];
        ks = new int[jml];
    }

    private static void inputNs() {
        for (int i = 0; i < jml; i++) {
            ns[i] = JOptionPane.showInputDialog("Masukkan nama siswa ke-" + (i + 1) + ":");
        }
    }

    private static void menu() {
        while (true) {
            String[] options = {
                "Tambah Pembayaran Kas",
                "Lihat Total Kas Siswa",
                "Cari Kas Tertinggi dan Terendah",
                "Hitung Persentase Kontribusi",
                "Hitung Rata-rata Kas",
                "Gunakan Saldo Kas",
                "Keluar/Reset"
            };
    
            String op = (String) JOptionPane.showInputDialog(
                null,
                "Pilih opsi:",
                "Menu Pembayaran Kas",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
            );
    
            if (op == null) break;
    
            switch (op) {
                case "Tambah Pembayaran Kas":
                    tambahKas();
                    break;
                case "Lihat Total Kas Siswa":
                    lihatKas();
                    break;
                case "Cari Kas Tertinggi dan Terendah":
                    cariKas();
                    break;
                case "Hitung Persentase Kontribusi":
                    hitungPersen();
                    break;
                case "Hitung Rata-rata Kas":
                    hitungRataRata();
                    break;
                case "Gunakan Saldo Kas":
                    gunakanKas();
                    break;
                case "Keluar/Reset":
                    resetData();
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opsi tidak valid.");
            }
        }
    }
    

    private static void resetData() {
        saldoKas = 0;
        JOptionPane.showMessageDialog(null, "Data telah direset.");
        main(null);
    }

    private static void tambahKas() {
        String nama = JOptionPane.showInputDialog("Masukkan nama siswa:");
        boolean ada = false;

        for (int i = 0; i < jml; i++) {
            if (ns[i].equalsIgnoreCase(nama)) {
                String input = JOptionPane.showInputDialog("Masukkan nominal pembayaran:");
                int byr = Integer.parseInt(input);
                ks[i] += byr;
                saldoKas += byr;
                JOptionPane.showMessageDialog(null, "Pembayaran berhasil ditambahkan.");
                ada = true;
                break;
            }
        }

        if (!ada) {
            JOptionPane.showMessageDialog(null, "Nama siswa tidak ditemukan.");
        }
    }

    private static void lihatKas() {
        StringBuilder data = new StringBuilder("Total kas setiap siswa:\n");
        for (int i = 0; i < jml; i++) {
            data.append("Nama: ").append(ns[i]).append(", Total Kas: ").append(ks[i]).append("\n");
        }
        data.append("Saldo Kas Tersisa: ").append(saldoKas);
        JOptionPane.showMessageDialog(null, data.toString());
    }

    private static void cariKas() {
        int max = ks[0];
        int min = ks[0];
        String nMax = ns[0];
        String nMin = ns[0];

        for (int i = 1; i < jml; i++) {
            if (ks[i] > max) {
                max = ks[i];
                nMax = ns[i];
            }
            if (ks[i] < min) {
                min = ks[i];
                nMin = ns[i];
            }
        }

        JOptionPane.showMessageDialog(null, "Kas Tertinggi: " + nMax + " dengan kas " + max +
                "\nKas Terendah: " + nMin + " dengan kas " + min);
    }

    private static void hitungPersen() {
        int total = 0;
        for (int k : ks) {
            total += k;
        }

        StringBuilder data = new StringBuilder("Persentase kontribusi kas:\n");
        for (int i = 0; i < jml; i++) {
            double persen = (double) ks[i] / total * 100;
            data.append("Nama: ").append(ns[i]).append(", Kontribusi: ").append(String.format("%.2f%%", persen)).append("\n");
        }

        JOptionPane.showMessageDialog(null, data.toString());
    }

    private static void hitungRataRata() {
        int total = 0;
        for (int k : ks) {
            total += k;
        }

        double rata = (double) total / jml;
        JOptionPane.showMessageDialog(null, String.format("Rata-rata kas siswa: %.2f", rata));
    }

    private static void gunakanKas() {
        String input = JOptionPane.showInputDialog("Masukkan nominal kas yang akan digunakan:");
        int jumlah = Integer.parseInt(input);

        if (jumlah <= saldoKas) {
            saldoKas -= jumlah;
            JOptionPane.showMessageDialog(null, "Penggunaan kas berhasil. Saldo kas tersisa: " + saldoKas);
        } else {
            JOptionPane.showMessageDialog(null, "Penggunaan kas gagal. Saldo kas tidak mencukupi.");
        }
    }
}
