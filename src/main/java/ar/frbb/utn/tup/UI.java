package ar.frbb.utn.tup;

import ar.frbb.utn.tup.characters.Character;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import static ar.frbb.utn.tup.Game.APP_NAME;
import static ar.frbb.utn.tup.Team.getCharacter;

public class UI {
    JFrame window;
    JPanel pantallaInicio, titulos, panelPrincipal, activeHero1, activeHero2, pelea, panelGanador;
    JLabel fondo, logo, loading, titulo, turno;
    JButton botonIniciar, botonPelear, botonVolverAJugar, proximoTurno;
    Character hero1, hero2, ganador;
    Image logoImage;
    Game.ChoiceHandler choiceHandler;
    Fight fight;
    final ClassLoader loader = Game.class.getClassLoader();
    public void crearUI(Game.ChoiceHandler cHandler) {
        fight = new Fight();
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

        /* TITULOS */
        pantallaInicio = crearPantallaInicio(cHandler);
        window.add(pantallaInicio);
        window.setVisible(true);
    }

    public void crearPantallaPrincipal(Game.ChoiceHandler cHandler) {
        panelPrincipal = crearPanelPrincipal(cHandler);
        window.add(panelPrincipal);
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

    public Character nuevoTurno(){
        Character ganador;
        try {
            ganador = fight.pelear(hero1, hero2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ganador;
    }

    void crearPantallaGanador(Character vencedor) {
        ganador = vencedor;
        panelGanador = crearPanelGanador(choiceHandler, ganador);
        window.add(panelGanador);
    }

    JPanel crearPantallaInicio(Game.ChoiceHandler cHandler) {
        Font fontLoading = new Font("Arial", Font.PLAIN, 17);
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
        loading = new JLabel("Cargado personajes...");
        loading.setBounds(350, 600, 200, 80);
        loading.setFont(fontLoading);
        loading.setAlignmentX(0.5f);

        pantallaInicio.add(logo);
        pantallaInicio.add(botonIniciar);
        pantallaInicio.add(loading);
        pantallaInicio.add(fondo);

        return pantallaInicio;
    }

    void seleccionarPersonajes() {
        hero1 = getPersonaje();
        hero2 = getPersonaje();
    }

    JPanel crearPanelPrincipal(Game.ChoiceHandler cHandler) {
        Font fontTitle = new Font("Masked Hero Demo", Font.PLAIN, 40);

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

        activeHero1 = buildHeroUI(hero1);
        activeHero2 = buildHeroUI(hero2);

        pelea = buildPeleaPanel();

        // AGREGA A PRINCIPAL
        panelPrincipal.add(titulos, BorderLayout.PAGE_START);
        panelPrincipal.add(activeHero1, BorderLayout.LINE_START);
        panelPrincipal.add(pelea, BorderLayout.CENTER);
        panelPrincipal.add(activeHero2, BorderLayout.LINE_END);
//        panelPrincipal.add(botonera, BorderLayout.CENTER);

        return panelPrincipal;
    }

    JPanel buildPeleaPanel() {
        String strTurno = "TURNO " + Fight.turno();
        Font fontTurno = new Font("Masked Hero Demo", Font.PLAIN, 30);
        Font fontLogs = new Font("Arial", Font.PLAIN, 17);

        JPanel pelea = new JPanel();
        pelea.setLayout(new BoxLayout(pelea, BoxLayout.Y_AXIS));

        turno = new JLabel(strTurno, SwingConstants.CENTER);
        turno.setFont(fontTurno);
        turno.setAlignmentX(0.5f);

        botonPelear = new JButton("PELEAR");
        botonPelear.setBounds(200, 450, 300, 80);
        botonPelear.setAlignmentX(0.5f);
        botonPelear.addActionListener(choiceHandler);
        botonPelear.setActionCommand("pelear");

        proximoTurno = new JButton("PROXIMO TURNO");
        proximoTurno.setBounds(200, 450, 300, 80);
        proximoTurno.setAlignmentX(0.5f);
        proximoTurno.addActionListener(choiceHandler);
        proximoTurno.setActionCommand("proximoTurno");

        pelea.add(turno);
        pelea.add(botonPelear);
        pelea.add(proximoTurno);
        ArrayList<String> logs = Log.logs;
        for(String log: logs) {
            JLabel label = new JLabel(log, SwingConstants.LEFT);
            label.setFont(fontLogs);
            label.setAlignmentX(0.5f);
            pelea.add(label);
        }

        return pelea;
    }

    void reBuildHeros() {
//        window.remove(panelPrincipal);
        panelPrincipal.remove(pelea);
        panelPrincipal.remove(activeHero1);
        panelPrincipal.remove(activeHero2);
        activeHero1 = buildHeroUI(hero1);
        activeHero2 = buildHeroUI(hero2);
        pelea = buildPeleaPanel();
//        activeHero1.revalidate();
//        activeHero2.revalidate();
        panelPrincipal.add(activeHero1, BorderLayout.LINE_START);
        panelPrincipal.add(activeHero2, BorderLayout.LINE_END);
        panelPrincipal.add(pelea, BorderLayout.CENTER);

        panelPrincipal.revalidate();
//        window.add(panelPrincipal);


//        panelPrincipal.revalidate();
//        panelPrincipal.repaint();
//        window.revalidate();
//        window.repaint();
//        panelPrincipal.setVisible(true);
    }

    void resetGame() {
        fight = new Fight();
    }

    JPanel buildHeroUI(Character hero) {
        JPanel activeHero = new JPanel();
        activeHero.setLayout(new BoxLayout(activeHero, BoxLayout.Y_AXIS));
        activeHero.add(getPersonajeImage(hero));
        activeHero.add(getPersonajeName(hero));
        activeHero.add(getPersonajeHp(hero));
        return activeHero;
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

        JPanel borderPanel = new JPanel();
        borderPanel.setLayout(new BorderLayout(10, 10));
        borderPanel.setOpaque(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(titulo);
        panel.add(getPersonajeImage(character));
        panel.add(getPersonajeName(character));
        panel.add(botonVolverAJugar);

        borderPanel.add(panel, BorderLayout.CENTER);

        return borderPanel;
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
        Font fontHeroName = new Font("Masked Hero Demo", Font.PLAIN, 45);
        JLabel hpLabel = new JLabel(String.valueOf(hero.hpActual()), SwingConstants.RIGHT);
        hpLabel.setForeground(Color.BLUE);
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
