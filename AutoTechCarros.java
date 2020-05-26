package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JRadioButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AutoTechCarros {

	private JFrame frame;
	private JTextField anoVeiculo;
	private JTextField modeloVeiculo;
	private JTextField placaVeiculo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoTechCarros window = new AutoTechCarros();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AutoTechCarros() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNomePag = new JLabel("Informa\u00E7\u00F5es do Ve\u00EDculo");
		lblNomePag.setForeground(Color.RED);
		lblNomePag.setBounds(140, 10, 167, 18);
		lblNomePag.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(lblNomePag);
		
		JLabel VeiculoDAO = new JLabel("Tipo de Ve\u00EDculo:");
		VeiculoDAO.setFont(new Font("Arial", Font.BOLD, 12));
		VeiculoDAO.setBounds(10, 39, 94, 14);
		panel.add(VeiculoDAO);
		
		JLabel ServicoDAO = new JLabel("Qual tipo de servi\u00E7o deseja: ");
		ServicoDAO.setFont(new Font("Arial", Font.BOLD, 12));
		ServicoDAO.setBounds(10, 165, 159, 14);
		panel.add(ServicoDAO);
		
		JLabel ano = new JLabel("Ano do Ve\u00EDculo:");
		ano.setFont(new Font("Arial", Font.BOLD, 12));
		ano.setBounds(245, 76, 87, 14);
		panel.add(ano);
		
		JButton Enviar = new JButton("Enviar");
		Enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Enviar.setForeground(Color.RED);
		Enviar.setFont(new Font("Arial", Font.BOLD, 12));
		Enviar.setBounds(169, 227, 103, 23);
		panel.add(Enviar);
		
		JLabel FabricanteDAO = new JLabel("Fabricante:");
		FabricanteDAO.setFont(new Font("Arial", Font.BOLD, 12));
		FabricanteDAO.setBounds(10, 118, 63, 14);
		panel.add(FabricanteDAO);
		
		JLabel placa = new JLabel("Placa do Ve\u00EDculo:");
		placa.setFont(new Font("Arial", Font.BOLD, 12));
		placa.setBounds(245, 39, 103, 14);
		panel.add(placa);
		
		JRadioButton Carro = new JRadioButton("Carro");
		Carro.setFont(new Font("Arial", Font.PLAIN, 11));
		Carro.setBounds(161, 35, 53, 23);
		panel.add(Carro);
		
		JRadioButton Moto = new JRadioButton("Moto");
		Moto.setFont(new Font("Arial", Font.PLAIN, 11));
		Moto.setBounds(105, 35, 109, 23);
		panel.add(Moto);
		
		JTextArea serviçoVeiculo = new JTextArea();
		serviçoVeiculo.setBounds(169, 160, 167, 39);
		panel.add(serviçoVeiculo);
		
		JCheckBox chckbxTermo = new JCheckBox("Todas as informa\u00E7\u00F5es acimas s\u00E3o verdadeiras");
		chckbxTermo.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxTermo.setBounds(93, 206, 263, 23);
		panel.add(chckbxTermo);
		
		JList list = new JList();
		list.setBounds(214, 131, -60, -62);
		panel.add(list);
		
		JComboBox FabricanteVeiculo = new JComboBox();
		FabricanteVeiculo.setModel(new DefaultComboBoxModel(new String[] {"Chevrolet", "Volkswagen", "Fiat", "Renault", "Ford", "Toyota", "Hyundai", "Jeep", "Honda", "Nissan", "Citro\u00EBn", "Mitsubishi", "Peugeot", "Chery", "BMW", "Mercedes-Benz", "Kia", "Audi", "Volvo", "Land Rover "}));
		FabricanteVeiculo.setBounds(83, 114, 108, 22);
		panel.add(FabricanteVeiculo);
		
		JLabel VeiculoModeloDAO = new JLabel("Modelo do Ve\u00EDculo:");
		VeiculoModeloDAO.setFont(new Font("Arial", Font.BOLD, 12));
		VeiculoModeloDAO.setBounds(10, 76, 109, 14);
		panel.add(VeiculoModeloDAO);
		
		anoVeiculo = new JTextField();
		anoVeiculo.setBounds(351, 73, 73, 20);
		panel.add(anoVeiculo);
		anoVeiculo.setColumns(10);
		
		modeloVeiculo = new JTextField();
		modeloVeiculo.setBounds(121, 73, 87, 20);
		panel.add(modeloVeiculo);
		modeloVeiculo.setColumns(10);
		
		placaVeiculo = new JTextField();
		placaVeiculo.setBounds(351, 39, 73, 20);
		panel.add(placaVeiculo);
		placaVeiculo.setColumns(10);
	}
}
