package ar.frbb.utn.tup;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import static ar.frbb.utn.tup.Game.APP_NAME;
import static ar.frbb.utn.tup.Team.getCharacter;

public class UI {
    JFrame window;
    JPanel pantallaInicio, titulos, panelPrincipal, activeHero1, activeHero2, pelea;
    JLabel fondo, logo, titulo, hero2Image, hero2Name, ataque, turno, vidaHero1, vidaHero2;
    JButton botonIniciar;
    Character hero1, hero2;
    String vida1, vida2;
    Image logoImage;
    File logoFile;
    final ClassLoader loader = Game.class.getClassLoader();
    public void crearUI(Game.ChoiceHandler cHandler) {
        String strTurno = "TURNO 4";
        String strAtaque = "<<< -120 Puntos! CRÍTICO!";
        Font fontTitle = new Font("Masked Hero Demo", Font.PLAIN, 40);
        Font fontTurno = new Font("Masked Hero Demo", Font.PLAIN, 30);
        Font fontSubtitle = new Font("Impact", Font.PLAIN, 45);
        Font fontAtaque = new Font("Impact", Font.PLAIN, 30);
        Random random = new Random();

        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                try {
                    File fontFile = new File(loader.getResource("MaskedHero.ttf").getFile());
                    Font newFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                    newFont = newFont.deriveFont(Font.PLAIN, 70f);

                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    ge.registerFont(newFont);
                } catch (IOException|FontFormatException e) {
                    System.out.println(e);
                }
            }
        });

        /* Frame principal */

        window = new JFrame();
        window.setTitle(APP_NAME);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        /*window.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("test.jpg")))));*/
        window.setSize(1080, 764);
        window.setResizable(false);
        window.setVisible(true);

        /* Logo */
        try {
            URL url = getClass().getResource("/images/logo.png");
            logoImage = new ImageIcon(ImageIO.read(url)).getImage();
            ImageIcon img = new ImageIcon(new ImageIcon(logoImage).getImage().getScaledInstance(350, 160, Image.SCALE_DEFAULT));
            logo = new JLabel(img);
            logo.setBounds(300,100,450, 260);
            logo.setOpaque(true);
            logo.setHorizontalAlignment(SwingConstants.CENTER);
            logo.setVerticalAlignment(SwingConstants.CENTER);
            logo.setBackground(Color.WHITE);
        } catch (Exception exp) {
            exp.printStackTrace();
        }

        /* TITULOS */
        pantallaInicio = new JPanel();
        pantallaInicio.setLayout(new BorderLayout(10, 10));
        pantallaInicio.setOpaque(false);
        fondo = cargarFondo();
        botonIniciar = new JButton("COMENZAR");
        botonIniciar.setBounds(400, 450, 200, 80);
        botonIniciar.addActionListener(cHandler);
        botonIniciar.setActionCommand("pelear");
        pantallaInicio.add(logo);
        pantallaInicio.add(botonIniciar);
        pantallaInicio.add(fondo);

        window.add(pantallaInicio);

        titulos = new JPanel();
        titulos.setLayout(new BorderLayout());
        titulos.setPreferredSize(new Dimension(1080,100));
        titulos.setBorder(new EmptyBorder(10,10,10,10));
        titulos.setVisible(true);

        titulo = new JLabel(APP_NAME, SwingConstants.CENTER);
        titulo.setFont(fontTitle);
        titulo.setForeground(Color.black);
        titulo.setBounds(52, 82, 1080, 100);
        titulo.setOpaque(false);

        titulos.add(titulo);

        /* CONTENIDO */
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setOpaque(false);

        hero1 = getCharacter(random.nextInt(731)+1);
        activeHero1 = new JPanel();
        activeHero1.setLayout(new BoxLayout(activeHero1, BoxLayout.Y_AXIS));
        activeHero1.add(getPersonajeImage(hero1));
        activeHero1.add(getPersonajeName(hero1));

        hero2 = getCharacter(random.nextInt(731)+1);
        activeHero2 = new JPanel();
        activeHero2.setLayout(new BoxLayout(activeHero2, BoxLayout.Y_AXIS));
        activeHero2.add(getPersonajeImage(hero2));
        activeHero2.add(getPersonajeName(hero2));

        pelea = new JPanel();
        pelea.setLayout(new BoxLayout(pelea, BoxLayout.Y_AXIS));

        turno = new JLabel(strTurno, SwingConstants.CENTER);
        turno.setFont(fontTurno);
        turno.setAlignmentX(0.5f);

        ataque = new JLabel(strAtaque, SwingConstants.CENTER);
        ataque.setAlignmentX(0.5f);
        ataque.setFont(fontAtaque);

        vida1 = Integer.toString(random.nextInt(23)*10+90);
        vidaHero1 = new JLabel(vida1, SwingConstants.CENTER);
        vidaHero1.setFont(fontSubtitle);
        vidaHero1.setAlignmentX(0.5f);

        vida2 = Integer.toString(random.nextInt(23)*10+90);
        vidaHero2 = new JLabel(vida2, SwingConstants.CENTER);
        vidaHero2.setFont(fontSubtitle);
        vidaHero2.setAlignmentX(0.5f);

        pelea.add(turno);
        pelea.add(vidaHero1);
        pelea.add(ataque);
        pelea.add(vidaHero2);

        // AGREGA A PRINCIPAL
        panelPrincipal.add(titulos, BorderLayout.PAGE_START);
        panelPrincipal.add(activeHero1, BorderLayout.LINE_START);
        panelPrincipal.add(pelea, BorderLayout.CENTER);
        panelPrincipal.add(activeHero2, BorderLayout.LINE_END);

        // AGREGA A FRAME
//        window.setVisible(true);
//        window.add(fondo);
        window.add(panelPrincipal);
        window.remove(fondo);
    }

    static JLabel cargarFondo() {
        Image bgImage = null;
        JLabel fondo = new JLabel();
        try {
            URL url = new URL("https://img.freepik.com/premium-vector/comic-versus-fight-cartoon-background_530597-710.jpg?w=1080");
            bgImage = ImageIO.read(url);
            fondo = new JLabel(new ImageIcon(bgImage));
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return fondo;
    }

    JLabel getPersonajeName(Character hero) {
        Font fontHeroName = new Font("Masked Hero Demo", Font.PLAIN, 23);
        JLabel nameLabel = new JLabel(hero.getName(), SwingConstants.CENTER);
        nameLabel.setOpaque(false);
        nameLabel.setFont(fontHeroName);
        return nameLabel;
    }
    JLabel getPersonajeImage(Character hero) {
        JLabel imagenLabel = new JLabel();
        Image image = null;

        try {
            URL url = new URL(hero.getImage());
            Image img = ImageIO.read(url);
            if(img == null) {
                File backup = new File(loader.getResource("/images/unknown.jpg").getFile());
                Image backupImage = ImageIO.read(backup);
            }
            image = new ImageIcon(img).getImage();
        } catch (Exception exp) {
            exp.printStackTrace();
        }

        ImageIcon img = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT));
        imagenLabel = new JLabel(img);
        imagenLabel.setOpaque(false);
        imagenLabel.setBounds(0,0,300, 400);
        return imagenLabel;
    }
}
