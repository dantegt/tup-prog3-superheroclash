package ar.frbb.utn.tup;

import ar.frbb.utn.tup.characters.Character;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import static ar.frbb.utn.tup.Game.APP_NAME;
import static ar.frbb.utn.tup.Team.getCharacter;

public class UI {
    JFrame window;
    JPanel pantallaInicio, titulos, panelPrincipal, activeHero1, activeHero2, pelea, botonera, panelGanador;
    JLabel fondo, logo, titulo, turno;
    JButton botonIniciar, botonPelear, botonVolverAJugar;
    Character hero1, hero2, ganador;
    Image logoImage;
    Game.ChoiceHandler choiceHandler;
    final ClassLoader loader = Game.class.getClassLoader();
    public void crearUI(Game.ChoiceHandler cHandler) {
        choiceHandler = cHandler;
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

        /* TITULOS */
        pantallaInicio = crearPantallaInicio(cHandler);
        window.add(pantallaInicio);

        panelPrincipal = crearPanelPrincipal(cHandler);
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

    void crearPantallaGanador() {
        ganador = getPersonaje();
        panelGanador = crearPanelGanador(choiceHandler, ganador);
        window.add(panelGanador);
    }

    JPanel crearPantallaInicio(Game.ChoiceHandler cHandler) {
        pantallaInicio = new JPanel();
        pantallaInicio.setLayout(new BorderLayout(10, 10));
        pantallaInicio.setOpaque(false);

        /* LOGO */
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
        fondo = cargarFondo();
        botonIniciar = new JButton("COMENZAR");
        botonIniciar.setBounds(400, 450, 200, 80);
        botonIniciar.addActionListener(cHandler);
        botonIniciar.setActionCommand("seleccionar");
        pantallaInicio.add(logo);
        pantallaInicio.add(botonIniciar);
        pantallaInicio.add(fondo);

        return pantallaInicio;
    }

    JPanel crearPanelPrincipal(Game.ChoiceHandler cHandler) {
        String strTurno = "TURNO 4";
        String strAtaque = "<<< -120 Puntos! CRÍTICO!";
        Font fontTitle = new Font("Masked Hero Demo", Font.PLAIN, 40);
        Font fontTurno = new Font("Masked Hero Demo", Font.PLAIN, 30);
        Font fontLogs = new Font("Arial", Font.PLAIN, 17);
        Random random = new Random();

        /* CONTENIDO */
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setOpaque(false);

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

        hero1 = getPersonaje();
        activeHero1 = new JPanel();
        activeHero1.setLayout(new BoxLayout(activeHero1, BoxLayout.Y_AXIS));
        activeHero1.add(getPersonajeImage(hero1));
        activeHero1.add(getPersonajeName(hero1));
        activeHero1.add(getPersonajeHp(hero1));

        hero2 = getPersonaje();
        activeHero2 = new JPanel();
        activeHero2.setLayout(new BoxLayout(activeHero2, BoxLayout.Y_AXIS));
        activeHero2.add(getPersonajeImage(hero2));
        activeHero2.add(getPersonajeName(hero2));
        activeHero2.add(getPersonajeHp(hero2));

        pelea = new JPanel();
        pelea.setLayout(new BoxLayout(pelea, BoxLayout.Y_AXIS));

        turno = new JLabel(strTurno, SwingConstants.CENTER);
        turno.setFont(fontTurno);
        turno.setAlignmentX(0.5f);

//        ataque = new JLabel(strAtaque, SwingConstants.CENTER);
//        ataque.setAlignmentX(0.5f);
//        ataque.setFont(fontAtaque);

//        vida1 = Integer.toString(random.nextInt(23)*10+90);
//        vidaHero1 = new JLabel(vida1, SwingConstants.CENTER);
//        vidaHero1.setFont(fontSubtitle);
//        vidaHero1.setAlignmentX(0.5f);
//
//        vida2 = Integer.toString(random.nextInt(23)*10+90);
//        vidaHero2 = new JLabel(vida2, SwingConstants.CENTER);
//        vidaHero2.setFont(fontSubtitle);
//        vidaHero2.setAlignmentX(0.5f);

        JLabel logs = new JLabel("Log de batalla", SwingConstants.CENTER);
        logs.setFont(fontLogs);
        logs.setAlignmentX(0.5f);

        botonera = new JPanel();
        botonera.setLayout(new BorderLayout(10, 10));
        botonera.setOpaque(false);

        botonPelear = new JButton("COMENZAR PELEA");
        botonPelear.setBounds(400, 450, 200, 80);
        botonPelear.addActionListener(cHandler);
        botonPelear.setActionCommand("pelear");

        botonera.add(botonPelear);

        pelea.add(turno);
        pelea.add(logs);
//        pelea.add(vidaHero1);
//        pelea.add(ataque);
//        pelea.add(vidaHero2);

        // AGREGA A PRINCIPAL
        panelPrincipal.add(titulos, BorderLayout.PAGE_START);
        panelPrincipal.add(activeHero1, BorderLayout.LINE_START);
        panelPrincipal.add(pelea, BorderLayout.CENTER);
        panelPrincipal.add(activeHero2, BorderLayout.LINE_END);
        panelPrincipal.add(botonera, BorderLayout.PAGE_END);

        return panelPrincipal;
    }

    JPanel crearPanelGanador(Game.ChoiceHandler cHandler, Character character) {
        Font fontHeroName = new Font("Masked Hero Demo", Font.PLAIN, 23);
        JLabel titulo = new JLabel("Ganador!", SwingConstants.CENTER);
        titulo.setOpaque(false);
        titulo.setFont(fontHeroName);

        botonVolverAJugar = new JButton("VOLVER A PELEAR!");
        botonVolverAJugar.setBounds(400, 450, 200, 80);
        botonVolverAJugar.addActionListener(cHandler);
        botonVolverAJugar.setActionCommand("nuevoJuego");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(titulo);
        panel.add(getPersonajeImage(character));
        panel.add(getPersonajeName(character));
        panel.add(botonVolverAJugar);

        return panel;
    }

    Character getPersonaje() {
        Random random = new Random();
        Character personaje = null;
        while (personaje == null) {
          System.out.println("intento");
          personaje = getCharacter(random.nextInt(731) + 1);
          System.out.println("personaje: " + personaje);
        }
        return personaje;
    }

    JLabel getPersonajeName(Character hero) {
        Font fontHeroName = new Font("Masked Hero Demo", Font.PLAIN, 23);
        JLabel nameLabel = new JLabel(hero.name(), SwingConstants.CENTER);
        nameLabel.setOpaque(false);
        nameLabel.setFont(fontHeroName);
        return nameLabel;
    }

    JLabel getPersonajeHp(Character hero) {
        Font fontHeroName = new Font("Masked Hero Demo", Font.PLAIN, 18);
        JLabel hpLabel = new JLabel(String.valueOf(hero.hpActual()), SwingConstants.RIGHT);
        hpLabel.setOpaque(false);
        hpLabel.setFont(fontHeroName);
        return hpLabel;
    }

    JLabel getPersonajeImage(Character hero) {
        JLabel imagenLabel = new JLabel();
        Image image = null;

        try {
            URL url = new URL(hero.image());
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
