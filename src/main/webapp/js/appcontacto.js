// Inicializa EmailJS (reemplaza con tus credenciales)
emailjs.init('alumno.554266@ies-azarquiel.es');

document.getElementById('contactForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    // Datos del formulario
    const templateParams = {
        nombre: document.getElementById('inputName').value,
        email: document.getElementById('inputEmail').value,
        cip: document.getElementById('inputCIP').value,
        asunto: document.getElementById('inputSubject').value,
        mensaje: document.getElementById('message').value
    };

    // Envía el correo (reemplaza SERVICE_ID y TEMPLATE_ID)
    emailjs.send('TU_SERVICE_ID', 'TU_TEMPLATE_ID', templateParams)
        .then(() => {
            alert('Mensaje enviado con éxito');
            document.getElementById('contactForm').reset(); // Limpia el formulario
        }, (error) => {
            alert('Error al enviar: ' + JSON.stringify(error));
        });
});