var Escola = Escola || {};

Escola.DialogoExclusao = (function() {

	function DialogoExclusao() {
		this.exclusaoBtn = $('.js-btn-exclusao')
	}
	
	DialogoExclusao.prototype.iniciar = function() {
		'use strict';
		this.exclusaoBtn.on('click', onExcluirClicado.bind(this));		
		
		if (window.location.search.indexOf('excluido') > -1) {
			Swal.fire({
			  position: 'top-end',
			  icon: 'success',
			  title: 'Seu registro foi excluido com sucesso.',
			  showConfirmButton: false,
			  timer: 1500
			});			
		}
	}
	
	function onExcluirClicado(ev){
		event.preventDefault();
		var btn = $(ev.currentTarget);
		var url = btn.data('url');
		var objeto = btn.data('objeto');	
			
		const swalWithBootstrapButtons = Swal.mixin({
		   customClass: {
			    confirmButton: 'btn btn-success',
			    cancelButton: 'btn btn-danger'
			  },
			    buttonsStyling: false
		});		

		swalWithBootstrapButtons.fire({
			  title: 'Tem certeza?',
			  text: 'Excluir "' + objeto + '"? Você não poderá reverter isto!',
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Sim, Exclua!',
			  cancelButtonText: 'Cancelar',
			  reverseButtons: true
			}).then((result) => {			
				if (result.value){
					$.ajax({
						url: url,
						method: 'DELETE',
						success: onExcluido.bind(this),
						error: onErroExcluir.bind(this)
					});							
				} else if (
				  /* Read more about handling dismissals below */
				  result.dismiss === Swal.DismissReason.cancel					
				) {			
					swalWithBootstrapButtons.fire(
						      'Cancelado',
						      'Seu Registro não foi excluido :)',
						      'info')					
				} 						
		});							
	}
	
	function onExcluido() {
	   var urlAtual = window.location.href;
	   var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
	   var novaUrl = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido';
		   	   
	   window.location = novaUrl;	   		   	   
	}
		
	function onErroExcluir(e) {		
		Swal.fire({
			  icon: 'error',
			  title: 'Oops...',
			  text: e.responseText
		});				
	}	
		
	return DialogoExclusao;	
}());

$(function() {
	var dialogo = new Escola.DialogoExclusao();
	dialogo.iniciar();
});

