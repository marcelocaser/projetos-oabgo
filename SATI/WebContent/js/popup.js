/**
  * Função responsável por acionar o popUp Modal ajustando ALTURA e LARGURA dinamicamente de acordo com
  * a resolução utilizada pelo Cliente.<b>
   
  * @param idModal String - Identificador do RICH:MODALPANEL para ajustar o componente.
  */
function showModalPopup(idModal){
	Richfaces.showModalPanel(idModal, {top:1});
}

/**
  * Fecha o ModalPanel do RichFaces
  * @param idModal - Id base do modal 
  */
function closePopup(idModal){
	Richfaces.hideModalPanel(idModal);
}

/**
 * Aciona uma janela MODAL no centro da página
 */
function showModalCentro(idModal){
	Richfaces.showModalPanel(idModal);
}


/**
 * Abre o modal com mensagem de erro
 * @param idElemento
 * @return
 */
function abrirModalMensagemErro(idElemento){
	showModalCentro(idElemento);
}

/**
  * Dispara a janela MODAL com temporizador.
  */
var idModal = "";
function abrirDivMensagem(idElemento){
	idModal = idElemento;
	showModalCentro(idElemento);
	setTimeout("acionarDivMensagem()",1000) 
}

function acionarDivMensagem(){
	setTimeout("fecharModal()", 650) 
}

function fecharModal(){
	closePopup(idModal);
}

/**
 * Configura e abre um modal panel
 * Parâmetros: porcentagemAreaVisivel - valor entre 0 e 100
 *             porcentagemLarguraMaiorAltura - valor entre 0 e 100 (0: os dois lados tem mesmo tamanho, 100: a altura tem tamanho zero - impraticavel) 
 */
function showModalPanel(idModalPanel, porcentagemAreaVisivel, porcentagemLarguraMaiorAltura){
	//captura o objeto screen da maquina cliente
	var tela = window.screen;
	//captura a largura disponivel de video(em pixels)
	var larguraDisponivel = tela.availWidth;
	//captura a altura disponivel de video(em pixels)
	var alturaDisponivel = tela.availHeight;
	//calcula-se a area disponivel(em pixels)
	var areaDisponivel = larguraDisponivel * alturaDisponivel;
	//supondo que o modal panel deva ocupar x% da área disponível
	var areaModal = (porcentagemAreaVisivel/100) * areaDisponivel;
	//extrai-se a raiz quadrada da área do modal para calcular o valor do lado do quadrado
	var tamanhoLado = Math.sqrt(areaModal);
	//calcula-se agora os valores da largura e da altura(é interessante que a largura seja uns 15% maior que a altura)
	var offSet = (porcentagemLarguraMaiorAltura/100) * tamanhoLado;
	var alturaModal = tamanhoLado - offSet;
	var larguraModal = tamanhoLado + offSet;
	//invoca-se o médoto da biblioteca do RichFaces para abrir o modal panel
	Richfaces.showModalPanel(idModalPanel, {width:larguraModal, height:alturaModal});
}

/**
 * Atualiza uma lista de Radios e Checks Box
 * retornando verdadeiro somente o componente selecionado.
 * Parâmetros: componente html
 */
function dataTableSelectAll(radio) {
    var id = radio.name.substring(radio.name.lastIndexOf(':'));
    var el = radio.form.elements;
    for (var i = 0; i < el.length; i++) {
    	if(el[i].checked){
    		el[i].checked = false;
    	}else{
          el[i].checked = true;
    	}
    }
    if(radio.checked){
    	radio.checked = false;
    }else{
    	radio.checked = true;
    }
}

/**
 * Atualiza uma lista de Radios e Checks Box
 * retornando verdadeiro somente o componente selecionado.
 * Parâmetros: componente html
 */
function dataTableSelectOneRadio(radio) {
	
    var id = radio.name.substring(radio.name.lastIndexOf(':'));
    var el = radio.form.elements;
    for (var i = 0; i < el.length; i++) {
        if (el[i].name.substring(el[i].name.lastIndexOf(':')) == id) {
            el[i].checked = false;
        }
    }
    radio.checked = true;
}

/**
 * Modifica o Status das TAB'S na DashBoard
 * @param id
 * @return
 */
function swapNav(id){
	var allDivs = document.getElementsByTagName("div");
	for(var i=0; i < allDivs.length; i++){
		if(allDivs[i].className == 'navOn' || allDivs[i].className == 'navOff'){
			if(allDivs[i].id == id){
				allDivs[i].className = 'navOn';
			}else{ 
				allDivs[i].className = 'navOff';
			}
		}
	}
}

function atualizaTab(id){
	document.getElementById(id).className = 'navOn';
}

