package core.utilitario;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import core.excecoes.ApplicationException;
import core.mensagem.MensagemLista;



/**
 * <b>Titulo:</b> Data.java <br>
 * <b>Descricao:</b>     <br>
 * <b>Projeto:</b> core <br>
 * 
 * @author Leonardo Peixoto
 */
public class Data {

	private static final String JANEIRO = "Janeiro";
	private static final String FEVEREIRO = "Fevereio";
	private static final String MARCO = "Março";
	private static final String ABRIL = "Abril";
	private static final String MAIO = "Maio";
	private static final String JUNHO = "Junho";
	private static final String JULHO = "Julho";
	private static final String AGOSTO = "Agosto";
	private static final String SETEMBRO = "Setembro";
	private static final String OUTUBRO = "Outubro";
	private static final String NOVEMBRO = "Novembro";
	private static final String DEZEMBRO = "Dezembro";

	private static final String JANEIRO_ABREV = "Jan";
	private static final String FEVEREIRO_ABREV = "Fev";
	private static final String MARCO_ABREV = "Mar";
	private static final String ABRIL_ABREV = "Abr";
	private static final String MAIO_ABREV = "Mai";
	private static final String JUNHO_ABREV = "Jun";
	private static final String JULHO_ABREV = "Jul";
	private static final String AGOSTO_ABREV = "Ago";
	private static final String SETEMBRO_ABREV = "Set";
	private static final String OUTUBRO_ABREV = "Out";
	private static final String NOVEMBRO_ABREV = "Nov";
	private static final String DEZEMBRO_ABREV = "Dez";

	public static final String MINUTO = "MN";
	public static final String HORA = "H";
	public static final String DIA = "D";
	public static final String MES = "M";
	public static final String ANO = "A";
	public static final String DATA_PADRAO = "dd/MM/yyyy";
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final String MM_DD_YYYY = "MM/dd/yyyy";
	public static final String DIA_HORAS = "dd/MM/yyyy HH:mm:ss";
	public static final String YYYY_MM_DD_HORAS = "yyyy/MM/dd HH:mm:ss";
	public static final String YYYY_MM_DD = "yyyy/MM/dd";
	public static final String DIA_MES_ANO = "dia/mes/ano";
	public static final String DIA_MES_ANO_ABREVIADO = "dia/mes/ano";
	public static final String HORAS_HH_MM_SS = "HH:mm:ss";

	public static final String DIA_MES_ANO_ABREVIADO_HORA = "dia/mes/ano hora";

	/**
	 * Faz a formatação da Data em um formato especificado
	 * <p>
	 * 
	 * @param Data
	 *            Data a ser formatada
	 * @param Padrao
	 *            Formato (formato Java) para datas. Ex: dd/MM/yyyy
	 * @return String
	 * @author Leonardo Peixoto
	 */
	private static String Formatar(Date Data, String Padrao) {
		if (Data == null) {
			return "";
		}
		if (Padrao.equals(DIA_MES_ANO)) {
			return aplicarMarcaraDiaMesAno(Data);
		} else if (Padrao.equals(DIA_MES_ANO_ABREVIADO)) {
			return aplicarMarcaraDiaMesAnoAbreviado(Data);
		} else if (Padrao.equals(DIA_MES_ANO_ABREVIADO_HORA)) {
			return aplicarMarcaraDiaMesAnoAbreviadoHora(Data);
		} else {
			SimpleDateFormat formatD = new SimpleDateFormat(Padrao);
			return formatD.format(Data);
		}
	}

	/**
	 * 
	 * Aplica uma mascara no formato DIA - MÊS - ANO com abreviatura
	 * 
	 * @param data
	 * @return -
	 * @return String
	 */
	private static String aplicarMarcaraDiaMesAnoAbreviadoHora(Date data) {
		StringBuffer retorno = new StringBuffer();

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		// insere o dia
		retorno.append(calendar.get(Calendar.DAY_OF_MONTH));
		retorno.append(" ");
		if (calendar.get(Calendar.MONTH) == 0) {
			retorno.append(JANEIRO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 1) {
			retorno.append(FEVEREIRO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 2) {
			retorno.append(MARCO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 3) {
			retorno.append(ABRIL_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 4) {
			retorno.append(MAIO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 5) {
			retorno.append(JUNHO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 6) {
			retorno.append(JULHO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 7) {
			retorno.append(AGOSTO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 8) {
			retorno.append(SETEMBRO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 9) {
			retorno.append(OUTUBRO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 10) {
			retorno.append(NOVEMBRO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 11) {
			retorno.append(DEZEMBRO_ABREV);
		}
		retorno.append(" ");
		retorno.append(calendar.get(Calendar.YEAR));
		retorno.append(" ");
		retorno.append(new SimpleDateFormat("HH:mm:ss").format(data));
		return retorno.toString();
	}

	/**
	 * 
	 * Aplica uma mascara no formato DIA - MÊS - ANO com abreviatura
	 * 
	 * @param data
	 * @return -
	 * @return String
	 */
	private static String aplicarMarcaraDiaMesAnoAbreviado(Date data) {
		StringBuffer retorno = new StringBuffer();

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		// insere o dia
		retorno.append(calendar.get(Calendar.DAY_OF_MONTH));
		if (calendar.get(Calendar.MONTH) == 0) {
			retorno.append(JANEIRO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 1) {
			retorno.append(FEVEREIRO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 2) {
			retorno.append(MARCO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 3) {
			retorno.append(ABRIL_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 4) {
			retorno.append(MAIO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 5) {
			retorno.append(JUNHO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 6) {
			retorno.append(JULHO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 7) {
			retorno.append(AGOSTO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 8) {
			retorno.append(SETEMBRO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 9) {
			retorno.append(OUTUBRO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 10) {
			retorno.append(NOVEMBRO_ABREV);
		} else if (calendar.get(Calendar.MONTH) == 11) {
			retorno.append(DEZEMBRO_ABREV);
		}
		retorno.append(calendar.get(Calendar.YEAR));
		return retorno.toString();
	}

	/**
	 * 
	 * Aplica uma mascara no formato DIA - MÊS - ANO
	 * 
	 * @param data
	 * @return -
	 * @return String
	 */
	private static String aplicarMarcaraDiaMesAno(Date data) {
		StringBuffer retorno = new StringBuffer();

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		// insere o dia
		retorno.append(calendar.get(Calendar.DAY_OF_MONTH));
		retorno.append(" de ");
		if (calendar.get(Calendar.MONTH) == 0) {
			retorno.append(JANEIRO);
		} else if (calendar.get(Calendar.MONTH) == 1) {
			retorno.append(FEVEREIRO);
		} else if (calendar.get(Calendar.MONTH) == 2) {
			retorno.append(MARCO);
		} else if (calendar.get(Calendar.MONTH) == 3) {
			retorno.append(ABRIL);
		} else if (calendar.get(Calendar.MONTH) == 4) {
			retorno.append(MAIO);
		} else if (calendar.get(Calendar.MONTH) == 5) {
			retorno.append(JUNHO);
		} else if (calendar.get(Calendar.MONTH) == 6) {
			retorno.append(JULHO);
		} else if (calendar.get(Calendar.MONTH) == 7) {
			retorno.append(AGOSTO);
		} else if (calendar.get(Calendar.MONTH) == 8) {
			retorno.append(SETEMBRO);
		} else if (calendar.get(Calendar.MONTH) == 9) {
			retorno.append(OUTUBRO);
		} else if (calendar.get(Calendar.MONTH) == 10) {
			retorno.append(NOVEMBRO);
		} else if (calendar.get(Calendar.MONTH) == 11) {
			retorno.append(DEZEMBRO);
		}
		retorno.append(" de ");
		retorno.append(calendar.get(Calendar.YEAR));
		return retorno.toString();
	}

	/**
	 * Formata a data de acordo com a máscara.
	 * 
	 * @param data
	 *            Date
	 * @param mascara
	 *            String
	 * @return String
	 * @author Leonardo Peixoto
	 */
	public static String formatar(Date data, String mascara) {
		return Formatar(data, mascara);
	}

	/**
	 * Retorna um objeto Date no formato DD/MM/YYYY HH:MM:SS
	 * 
	 * @param data
	 * @param pattern
	 * @return Date
	 */
	public static Date formatDate(Date data, String pattern) {
		try {
			SimpleDateFormat DIA_HORAS = new SimpleDateFormat(pattern);
			String data2 = DIA_HORAS.format(data);
			return DIA_HORAS.parse(data2);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Converte uma String para tipo Date
	 * <p>
	 * 
	 * @param Data
	 *            Data a ser convertida
	 * @param Padrao
	 *            Formato (formato Java) para datas. Ex: dd/MM/yyyy
	 * @return Date
	 * @author Leonardo Peixoto
	 */
	public static Date toDate(String Data, String Padrao) {
		Date DataAux = new Date();
		SimpleDateFormat FormataDT = new SimpleDateFormat(Padrao);

		try {
			DataAux = FormataDT.parse(Data);
		} catch (java.text.ParseException E) {
			return null;
		}
		return DataAux;
	}

	/**
	 * Converte tres Strings (dia, mes, ano) para tipo Date Aqui já é feito a
	 * validação da data, se inválida retorna null
	 * <p>
	 * 
	 * @param dia
	 *            Dia
	 * @param mes
	 *            Mes
	 * @param ano
	 *            Ano
	 * @return Objeto Date
	 * @author Leonardo Peixoto
	 */
	public static Date toDate(String dia, String mes, String ano) {
		Numero num = new Numero();

		if (!isDate(dia, mes, ano)) {
			// erroMensagem = "Data Inválida";
			return null;
		} else {
			String data = num.formatar(Integer.parseInt(dia), "00") + "/"
					+ num.formatar(Integer.parseInt(mes), "00") + "/"
					+ num.formatar(Integer.parseInt(ano), "0000");
			return toDate(data, "dd/MM/yyyy");
		}
	}

	/**
	 * Retorna o objeto Date com as horas zeradas (00:00:0000)
	 * 
	 * @param data
	 *            Date a ser convertida.
	 * @return Date
	 * @author Leonardo Peixoto
	 */
	public static Date retirarHoras(Date data) {
		SimpleDateFormat FormataDT = new SimpleDateFormat(Data.DATA_PADRAO);

		try {
			return FormataDT.parse(Data.formatar(data, Data.DATA_PADRAO));
		} catch (java.text.ParseException E) {
			return null;
		}
	}

	/**
	 * Converte entre duas datas formatadas
	 * <p>
	 * 
	 * @param data
	 *            Data formatada
	 * @param formatoOrigem
	 *            formato original
	 * @param formatoDestino
	 *            formato destino
	 * @return data formatada para o novo formato
	 * @author Leonardo Peixoto
	 */
	public static String converter(String data, String formatoOrigem,
			String formatoDestino) {
		return formatar(toDate(data, formatoOrigem), formatoDestino);
	}

	/**
	 * Verifica se a data é válida (paramatros : Dia, Mes e Ano)
	 * 
	 * @param xdia
	 *            String
	 * @param xmes
	 *            String
	 * @param xano
	 *            String
	 * @return boolean
	 * @author Leonardo Peixoto
	 */
	public static boolean isDate(String xdia, String xmes, String xano) {
		int dia = 0;
		int mes = 0;
		int ano = 0;

		try {
			dia = Integer.valueOf(xdia).intValue();
			mes = Integer.valueOf(xmes).intValue();
			ano = Integer.valueOf(xano).intValue();
		} catch (Exception e) {
			return false;
		}

		boolean bissexto = (ano % 4) == 0;
		boolean resp = false;

		if (ano <= 0 || ano > 2100)
			return resp;
		if (mes < 1 || mes > 12)
			return resp;

		switch (mes) {
		case 2:
			if (bissexto == true && mes == 2 && dia > 29)
				break;
			if (bissexto == false && mes == 2 && dia > 29)
				break;
			resp = true;
			break;
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if (dia > 31)
				break;
			resp = true;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			if (dia > 30)
				break;
			resp = true;
		}

		return resp;
	}
	
	/**
	 * Verifica se a data é válida (paramatros data no formato String)
	 * 
	 * @param Data String
	 * @return boolean
	 */
	public static boolean isDate(String Data) {
		String dia = "";
		String mes = "";
		String ano = "";
		String car = "";
		int pos = 0, posant = 0;
		Texto T = new Texto();

		car = "/";
		pos = T.PosicaoString(Data, car);

		if (pos == 0) {
			car = "-";
			pos = T.PosicaoString(Data, car);
		}

		if (pos == 0)
			return false;

		pos = T.PosicaoString(Data, car);

		if (pos == 0)
			return false;

		dia = Data.substring(0, pos - 1);
		posant = pos;
		pos = T.posicaoString(Data, car, pos + 1);

		if (pos == 0)
			return false;

		mes = Data.substring(posant, pos - 1);

		if (pos < Data.length())
			ano = Data.substring(pos);
		else
			return false;

		return isDate(dia, mes, ano);
	}
	
	/**
	 * Compara duas datas.
	 * Se as datas forem iguais, retorna 0;
	 * Se a data do primeiro argumento for menor que a data do segundo argumento, retorna -1;
	 * Se a data do primeiro argumento for maior que a data do segundo argumento, retorna 1.
	 * 
	 * @param dataInicial - Date
	 * @param dataFinal - Date
	 * @return int
	 */
	public static int comparaDatas(Date dataInicial, Date dataFinal) {
		SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataInicial = simple.parse(simple.format(dataInicial));
			dataFinal = simple.parse(simple.format(dataFinal));
		} catch (ParseException e) {
			throw new ApplicationException("Utilitario: Erro ao converter data");
		}
		
		return dataInicial.compareTo(dataFinal);
	}

	/**
	 * Compara duas horas.
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * Se as horas forem iguais, retorna 0;
	 * Se a hora do primeiro argumento for menor que a hora do segundo argumento, retorna -1;
	 * Se a hora do primeiro argumento for maior que a hora do segundo argumento, retorna 1.
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param Time
	 *            (Time "HH:MM:SS")
	 * @param Time
	 *            (Time "HH:MM:SS")
	 * @return int]
	 */
	public static int comparaHoras(Time horaBase, Time horaComparada) {
		SimpleDateFormat simple = new SimpleDateFormat("HH:mm:ss");
		Date dataBase = new Date(horaBase.getTime());
		Date dataComparada = new Date(horaComparada.getTime());

		try {
			dataBase = simple.parse(simple.format(dataBase));
			dataComparada = simple.parse(simple.format(dataComparada));
		} catch (ParseException e) {
			throw new ApplicationException("Util:Erro ao converter hora");
		}

		return dataBase.compareTo(dataComparada);
	}

	/**
	 * Retorna a diferenca entre duas datas.
	 * 
	 * @param Date
	 *            (Data "DD/MM/AAAA HH:MM")
	 * @param Date
	 *            (Data "DD/MM/AAAA HH:MM")
	 * @param int
	 *            <br>
	 *            Calendar.DAY_OF_MONTH - retorna a diferença em dias; <br>
	 *            Calendar.WEEK_OF_MONTH - retorna a diferença em semanas; <br>
	 *            Calendar.MONTH - retorna a diferença em meses; <br>
	 *            Calendar.DAY_OF_YEAR - retorna a diferença em anos.
	 * @param TimeZone
	 *            ("GMT-03:00")
	 * @return int
	 */
	public static int diferencaDatas(Date sdate1, Date sdate2, int tipoRetorno,
			TimeZone tz) {
		Date date1 = sdate1;
		Date date2 = sdate2;

		Calendar cal1 = null;
		Calendar cal2 = null;

		if (tz == null) {
			cal1 = Calendar.getInstance();
			cal2 = Calendar.getInstance();
		} else {
			cal1 = Calendar.getInstance(tz);
			cal2 = Calendar.getInstance(tz);
		}

		// data diferente pode ter offset diferente
		cal1.setTime(date1);
		long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET)
				+ cal1.get(Calendar.DST_OFFSET);

		cal2.setTime(date2);
		long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET)
				+ cal2.get(Calendar.DST_OFFSET);

		// Usa calculos com inteiros, trunca os decimais
		int hr1 = (int) (ldate1 / 3600000); // 60*60*1000
		int hr2 = (int) (ldate2 / 3600000);

		int days1 = (int) hr1 / 24;
		int days2 = (int) hr2 / 24;

		int dateDiff = days2 - days1;
		int weekOffset = (cal2.get(Calendar.DAY_OF_WEEK) - cal1
				.get(Calendar.DAY_OF_WEEK)) < 0 ? 1 : 0;
		int weekDiff = dateDiff / 7 + weekOffset;
		int yearDiff = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
		int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH)
				- cal1.get(Calendar.MONTH);

		if (tipoRetorno == Calendar.DAY_OF_MONTH
				|| tipoRetorno == Calendar.DAY_OF_WEEK
				|| tipoRetorno == Calendar.DAY_OF_WEEK_IN_MONTH
				|| tipoRetorno == Calendar.DAY_OF_YEAR) {
			return dateDiff;
		} else if (tipoRetorno == Calendar.WEEK_OF_MONTH
				|| tipoRetorno == Calendar.WEEK_OF_YEAR) {
			return weekDiff;
		} else if (tipoRetorno == Calendar.MONTH) {
			return monthDiff;
		} else if (tipoRetorno == Calendar.YEAR) {
			return yearDiff;
		} else {
			return dateDiff;
		}
	}

	/**
	 * Retorna parte da data.
	 * 
	 * @param Date
	 *            (Data "DD/MM/AAAA HH:MM")
	 * @param int
	 *            <br>
	 *            Calendar.DAY_OF_MONTH - dia do mês; <br>
	 *            Calendar.WEEK_OF_MONTH - dia da semana; <br>
	 *            Calendar.MONTH - mes; <br>
	 *            Calendar.YEAR - ano.
	 * @param TimeZone
	 *            ("GMT-03:00")
	 * @return Integer
	 */
	public static Integer retornaParteData(Date data, int tipoRetorno) {
		SimpleDateFormat DIA_HORAS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00"));

		try {
			data = DIA_HORAS.parse(DIA_HORAS.format(data));
		} catch (ParseException e) {
			throw new ApplicationException("util.erroConverterData");
		}
		cal1.setTime(data);

		if (tipoRetorno == Calendar.MONTH) {
			return new Integer(cal1.get(tipoRetorno) + 1);
		} else {
			return new Integer(cal1.get(tipoRetorno));
		}
	}

	/**
	 * Retorna a uma Date incrementada em dias.
	 * 
	 * @param Date
	 *            Data a ser incrementada.
	 * @param int
	 *            Quantidade de dias para incrementar na data.
	 * @param TimeZone
	 *            ("GMT-03:00")
	 * @return Date
	 */
	public static Date incrementaDias(Date dataAtual, int dias, TimeZone tz) {
		Calendar calendar = null;

		if (tz == null) {
			calendar = Calendar.getInstance();
		} else {
			calendar = Calendar.getInstance(tz);
		}

		calendar.setTime(dataAtual);
		calendar.add(Calendar.DATE, dias);
		return calendar.getTime();
	}

	/**
	 * Converte um período de tempo, dado em horas, minutos ou segundos, em
	 * milisegundos.
	 * <p>
	 * O período de tempo precisa ser representado por um número inteiro
	 * terminado em: <br>
	 * <b>h</b> ou <b>H</b> para converter horas; <br>
	 * <b>m</b> ou <b>M</b> para converter minutos; <br>
	 * <b>s</b> ou <b>S</b> para converter segundos. Exemplo:
	 * 
	 * <pre>
	 * long period = Util.toMilliseconds(&quot;2H&quot;);
	 * </pre>
	 * 
	 * @param timePeriod
	 *            Período de tempo.
	 * @return long Período de tempo convertido em milisegundos.
	 */
	public static long toMilliseconds(String timePeriod) {
		String errorMessage = "Time period: \""
				+ timePeriod
				+ "\". Must be a long integer finished with H, h, M, m, S or s.";
		try {
			if (timePeriod.toUpperCase().endsWith("H")) {
				return Long.parseLong(timePeriod.substring(0, timePeriod
						.length() - 1)) * 1000 * 60 * 60;
			} else if (timePeriod.toUpperCase().endsWith("M")) {
				return Long.parseLong(timePeriod.substring(0, timePeriod
						.length() - 1)) * 1000 * 60;
			} else if (timePeriod.toUpperCase().endsWith("S")) {
				return Long.parseLong(timePeriod.substring(0, timePeriod
						.length() - 1)) * 1000;
			} else {
				throw new IllegalArgumentException(errorMessage);
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(errorMessage);
		}

	}

	/**
	 * Transforma a string em um Date de acordo com o formato do Locale em
	 * questão no padrão resumido (Ex.: pt_BR => dd/MM/yy).
	 * 
	 * @param String
	 *            data a ser transformada em objeto Date.
	 * @param String
	 *            mensagem a ser armazenada em MensagemLista caso ocorra erro.
	 * @param MensagemLista
	 *            armazena mensagens caso ocorra erro durante a execução do
	 *            método.
	 * @param Locale
	 *            localidade à qual a data deve ser formatada.
	 * @return Date data formatada de acordo com a localidade e tipo.
	 */
	public static Date parseData(String data, String mensagemData,
			MensagemLista mensagens, Locale locale) {
		try {
			if (data != null) {
				DateFormat dateFormat = DateFormat.getDateInstance(
						DateFormat.SHORT, locale);
				return dateFormat.parse(data);
			} else {
				return null;
			}

		} catch (ParseException pe) {
			mensagens.addMensagem(mensagemData);
			return null;
		}
	}

	/**
	 * Transforma a string em um Date de data e hora.
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * &quot;MMM dd, yyyy HH:00:00&quot;
	 * &quot;dd/MM/yyyy HH:00:00&quot;
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param String
	 *            data a ser transformada em objeto Date.
	 * @param String
	 *            hora a ser atribuída ao objeto Date a ser criado.
	 * @param String
	 *            mensagem a ser armazenada em MensagemLista caso ocorra erro.
	 * @param MensagemLista
	 *            armazena mensagens caso ocorra erro durante a execução do
	 *            método.
	 * @param Locale
	 *            localidade à qual a data deve ser formatada.
	 * @return Date data formatada de acordo com a localidade.
	 */
	public static Date parseDataHora(String data, String hora,
			String mensagemData, MensagemLista mensagens, Locale locale) {
		try {
			if (locale.getLanguage().toString().equals("en")) {
				return new SimpleDateFormat("MMM dd, yyyy HH:mm:ss").parse(data
						+ " " + hora + ":00");
			} else {
				return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(data
						+ " " + hora + ":00");
			}
		} catch (ParseException pe) {
			mensagens.addMensagem(mensagemData);
			return null;
		}
	}

	/**
	 * Compara a data e a hora informada com a data e a hora atual. Se a data e
	 * hora atual for maior que a data e hora informada então retorna <b>true</b>.
	 * 
	 * @param String
	 *            (Data "DD/MM/AAAA HH:MM")
	 * @return boolean
	 */
	public static boolean isVencida(Date data) {
		Date data1 = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
		try {
			data1 = simple.parse(simple.format(data1));
			data = simple.parse(simple.format(data));
		} catch (ParseException e) {
			throw new ApplicationException("Util:Erro ao converter data");
		}
		if (data1.compareTo(data) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Compara duas datas. Se a primeira data e hora forem maiores do que a
	 * segunda data e hora então retorna <b>true</b>.
	 * 
	 * @param Date
	 *            (Data "DD/MM/AAAA HH:MM")
	 * @param Date
	 *            (Data "DD/MM/AAAA HH:MM")
	 * @return boolean
	 */
	public static boolean isDentroDoPrazo(Date dataPrevista, Date dataConclusao) {
		SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataPrevista = simple.parse(simple.format(dataPrevista));
			dataConclusao = simple.parse(simple.format(dataConclusao));
		} catch (ParseException e) {
			throw new ApplicationException("Util:Erro ao converter data");
		}

		if (dataPrevista.compareTo(dataConclusao) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Compara duas datas. Se a primeira data e hora forem menores do que a
	 * segunda data e hora então retorna <b>true</b>.
	 * 
	 * @param Date
	 *            (Data "DD/MM/AAAA HH:MM")
	 * @param Date
	 *            (Data "DD/MM/AAAA HH:MM")
	 * @return boolean
	 */
	public static boolean isDataMenor(Date dataPrevista, Date dataConclusao) {
		if (comparaDatas(dataPrevista, dataConclusao) < 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Compara duas datas. Se a primeira data e hora forem maiores do que a
	 * segunda data e hora então retorna <b>true</b>.
	 * 
	 * @param Date
	 *            (Data "DD/MM/AAAA HH:MM")
	 * @param Date
	 *            (Data "DD/MM/AAAA HH:MM")
	 * @return boolean
	 */
	public static boolean isDataMaior(Date dataPrevista, Date dataConclusao) {
		if (comparaDatas(dataPrevista, dataConclusao) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adiciona (MN)Minutos, (H)horas,  (D) dias, (M)Meses ou (A) Anos a uma data passada como parâmetro
	 * 
	 * @param pDataE-  Date
	 * @param pQtd -  int
	 * @param strTipo -  String
	 * @return Date
	 * @author Leonardo Peixoto
	 */
	public static Date adicionar(Date pDataE, int pQtd, String strTipo) {
		Calendar DataR = new GregorianCalendar();
		DataR.setTime(pDataE); // Converte a data para o Cal Gregoriano p/
								// fazer a adição dos dias

		if (strTipo.equalsIgnoreCase(DIA)) {
			DataR.add(Calendar.DAY_OF_MONTH, pQtd); // Faz a soma dos dias
		}else if (strTipo.equalsIgnoreCase(MES)) {
			DataR.add(Calendar.MONTH, pQtd); // Faz a soma dos meses
		}else if (strTipo.equalsIgnoreCase(ANO)) {
			DataR.add(Calendar.YEAR, pQtd); // Faz a soma dos anos
		}else if (strTipo.equalsIgnoreCase(HORA)) {
			DataR.add(Calendar.HOUR, pQtd); // Faz a soma das horas
		}else if (strTipo.equalsIgnoreCase(MINUTO)) {
			DataR.add(Calendar.MINUTE, pQtd); // Faz a soma dos Minutos
		}

		return DataR.getTime();
	}

	/**
	 * Retorna o ano atual
	 * 
	 * @param data - {@link Date} - Data a ser manipulada
	 * @param constante - {@link int} - Constante que representa o valor a ser retornado.
	 * 			EX: Calendar.YEAR -> ANO. <br>
	 * 				Calendar.MONTH -> MES. <br>
	 * 				Calendar.DAY_OF_MONTH -> DIA DO MES. <br>
	 * 				Calendar.DAY_OF_MONTH -> DIA DO MES. <br>
	 * 				Calendar.HOUR -> HORAS. <br>
	 * 				Calendar.MINUTE -> MINUTOS. <br>
	 * 				Calendar.SECOND -> SEGUNDOS. <br>
	 * @return int
	 */
	public static int get(Date data, int constante) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		return calendar.get(constante);
	}
	
	/**
	 * Retorna o ano atual
	 * 
	 * @return int
	 */
	public static int getAno() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * Retorna o Mês atual
	 * 
	 * @return int
	 */
	public static int getMes() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * Retorna o dia do Mês atual
	 * 
	 * @return int
	 */
	public static int getDiaMes() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Retorna o dia da Semana atual
	 * 
	 * @return int
	 */
	public static int getDiaSemana() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * Retorna a hora atual no formato "HH:MM:SS"
	 * 
	 * @return String
	 */
	public static String getHoras() {
		Calendar calendar = new GregorianCalendar();
		int hora = calendar.get(Calendar.HOUR);
		int minutos = calendar.get(Calendar.MINUTE);
		int segundos = calendar.get(Calendar.SECOND);
		String horas = "" + hora + "" + minutos + "" + segundos;
		return horas;
	}

	/**
	 * Retorna o trimestre de uma data informada
	 * <p>
	 * 
	 * @param data
	 *            Data válida
	 * @return trimestre ao qual a data pertence
	 * @author Leonardo Peixoto
	 */
	public static int getTrimestre(Date data) {

		Calendar aux = new GregorianCalendar();
		aux.setTime(data);

		switch (aux.get(Calendar.MONTH)) {
		case 0:
		case 1:
		case 2:
			return 1;
		case 3:
		case 4:
		case 5:
			return 2;
		case 6:
		case 7:
		case 8:
			return 3;
		case 9:
		case 10:
		case 11:
			return 4;
		default:
			return 0;
		}
	}

	/**
	 * Retorna o último dia do mes informado.
	 * 
	 * @param mes
	 *            Mes
	 * @param ano
	 *            Ano
	 * @return o último dia do mes informado
	 * @author Leonardo Peixoto
	 */
	public static int getUltimoDiaMes(int mes, int ano) {
		int dia = 0;

		if ((mes == 1) || (mes == 3) || (mes == 5) || (mes == 7) || (mes == 8)
				|| (mes == 10) || (mes == 12)) {
			dia = 31;
		} else if ((mes == 4) || (mes == 6) || (mes == 9) || (mes == 11)) {
			dia = 30;
		} else if (mes == 2) {
			if ((ano % 4) != 0) {
				dia = 28;
			} else if ((ano % 100) != 0) {
				dia = 29;
			} else if ((ano % 400) != 0) {
				dia = 28;
			} else {
				dia = 29;
			}
		} else {
			dia = 0;
		}

		return dia;
	}

	/**
	 * Retorna a diferença entre duas datas.
	 * 
	 * @param dataInicial
	 *            data inicial.
	 * @param dataFinal
	 *            data final
	 * @return int - a resposta em dias da diferença entre duas datas.
	 * @author Leonardo Peixoto
	 */
	public static int calculaDiferencaAbsolutaEntreDatas(String dataInicial,
			String dataFinal) {
		GregorianCalendar gc1 = new GregorianCalendar(Integer
				.parseInt(dataInicial.substring(4, dataInicial.length())),
				Integer.parseInt(dataInicial.substring(2, 4)), Integer
						.parseInt(dataInicial.substring(0, 2)));
		GregorianCalendar gc2 = new GregorianCalendar(Integer
				.parseInt(dataFinal.substring(4, dataFinal.length())), Integer
				.parseInt(dataFinal.substring(2, 4)), Integer
				.parseInt(dataFinal.substring(0, 2)));

		if (gc1.after(gc2))
			return (int) ((gc1.getTime().getTime() - gc2.getTime().getTime()) / 1000 / 60 / 60 / 24);
		else
			return (int) ((gc2.getTime().getTime() - gc1.getTime().getTime()) / 1000 / 60 / 60 / 24);
	}

	/**
	 * Retorna a diferença entre duas datas em dias.
	 * 
	 * @param dataInicial
	 *            data inicial.
	 * @param dataFinal
	 *            data final
	 * @return int - a diferença entre duas datas em dia.
	 * @author Leonardo Peixoto
	 */
	public static int calculaDiferencaEntreDatas(Date dataInicial,
			Date dataFinal) {
		return Data.calculaDiferencaEntreDatas(Data.formatar(dataInicial,
				"ddMMyyyy"), Data.formatar(dataFinal, "ddMMyyyy"));
	}

	/**
	 * Retorna a diferença entre duas datas em dias.
	 * 
	 * @param dataInicial
	 *            data inicial.
	 * @param dataFinal
	 *            data final
	 * @return int - a diferença entre duas datas em dia.
	 * @author Leonardo Peixoto
	 */
	public static int calculaDiferencaEntreDatas(String dataInicial,
			String dataFinal) {
		long DAY = 24L * 60L * 60L * 1000L;
		SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
		Date d1 = null;
		Date d2 = null;

		try {
			d1 = df.parse(dataInicial);
			d2 = df.parse(dataFinal);
		} catch (Exception e) {
		}

		int dif = (int) ((d2.getTime() - d1.getTime()) / DAY);
		return dif;
	}

	/**
	 * Retorna a diferença entre duas datas em meses.
	 * 
	 * @param dataInicial
	 *            Data inicial.
	 * @param dataFinal
	 *            Data final.
	 * @return int - Diferença em meses entre as datas fornecidas.
	 * @author Leonardo Peixoto
	 */
	public static int calculaDiferencaEntreDatasEmMeses(Date dataInicial,
			Date dataFinal) {
		int mesIni = Integer.parseInt(formatar(dataInicial, "MM"));
		int anoIni = Integer.parseInt(formatar(dataInicial, "yyyy"));
		int mesFim = Integer.parseInt(formatar(dataFinal, "MM"));
		int anoFim = Integer.parseInt(formatar(dataFinal, "yyyy"));

		return ((anoFim - anoIni) * 12) - (-(mesFim - mesIni));
	}

	/**
	 * Retorna a diferença entre duas datas em ano.
	 * 
	 * @param dataInicial
	 *            data inicial.
	 * @param dataFinal
	 *            data final
	 * @return a diferença entre duas datas em ano.
	 * @author Leonardo Peixoto
	 */
	public static int retornarDiferencaEntreDatasEmAnos(String dataInicial,
			String dataFinal) {
		String anoInicial = dataInicial.substring(4, dataInicial.length());
		String anoFinal = dataFinal.substring(4, dataFinal.length());
		return Integer.parseInt(anoFinal) - Integer.parseInt(anoInicial);
	}

	/**
	 * Retorna uma nova data a partir dos dias adicionados
	 * 
	 * @param data
	 *            data
	 * @param dias
	 *            dias para adicionar
	 * @return retorna uma nova data a partir dos dias adicionados ou null se a
	 *         data informada for nula.
	 * @author Leonardo Peixoto
	 */
	public static java.util.Date retornarDataAdicionadaDias(
			java.util.Date data, int dias) {
		if (data == null)
			return null;

		GregorianCalendar dataAux = new GregorianCalendar();
		dataAux.setTime(data);
		dataAux.add(GregorianCalendar.DATE, dias);
		return dataAux.getTime();
	}

	/**
	 * Retorna uma nova data a partir dos dias adicionados.
	 * 
	 * @param data
	 *            Date
	 * @param dias
	 *            dias para adicionar
	 * @return Date - retorna uma nova data a partir dos dias adicionados ou
	 *         null se a data informada for nula.
	 * @author Leonardo Peixoto
	 */
	public static Date retornarDataSubtraidaDias(java.util.Date data, int dias) {
		if (data == null)
			return null;

		GregorianCalendar dataAux = new GregorianCalendar();
		dataAux.setTime(data);
		dataAux.add(GregorianCalendar.DATE, (dias * -1));
		return dataAux.getTime();
	}

	/**
	 * Converte uma data para o dia Juliano.
	 * 
	 * @param data
	 *            Date
	 * @return long
	 * @author Leonardo Peixoto
	 */
	public static long converteDataDiaJuliano(Date data) {
		Date dataGregoriana = Data.toDate("15", "10", "1582");
		Date dataJuliana = Data.toDate("04", "10", "1582");

		Calendar calendario = new GregorianCalendar();
		calendario.setTime(data);

		int julDia = calendario.get(GregorianCalendar.DATE);
		int julMes = calendario.get(GregorianCalendar.MONTH);
		int julAno = calendario.get(GregorianCalendar.YEAR);
		long julC = 0;
		long julD = 0;
		long julE = 0;

		if (calendario.get(GregorianCalendar.ERA) == GregorianCalendar.BC) { // Era
																				// antes
																				// de
																				// Cristo
			julAno = (julAno * (-1)) + 1;
		}

		if (julMes < 3) {
			julAno = julAno - 1;
			julMes = julMes + 12;
		}

		// Se a data for igual ou posterior a 15/10/1582 (Calendário Gregoriano)
		if (!data.before(dataGregoriana)) {
			julC = 2 - ((long) julAno / 100) + ((long) julAno / 4);
		}
		// Se a data for igual ou anterior a 04/10/1582 (Calendário Juliano)
		else if (!data.after(dataJuliana)) {
			julC = 0;
		}

		julD = (long) 365.25 * (julAno + 4716);
		julE = (long) 30.6001 * (julMes + 1);

		return (long) julD + julE + julDia + julC - 1524;
	}
	
	/**
	 * Zera a hora, os minutos e segundos de uma data
	 * 
	 * @param data - Date
	 * @return Date
	 */
	public static Date zerarHoraMinutoSegundo(Date data) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		calendar.set(Calendar.AM_PM, 0);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * Completa o dia. Seta a hora para 23:59:59
	 * 
	 * @param data - Date
	 * @return Date
	 */
	public static Date setLimiar(Date data){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		calendar.set(Calendar.AM_PM, 0);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
}
