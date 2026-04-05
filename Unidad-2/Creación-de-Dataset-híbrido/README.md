1. Descripción General
El presente proyecto tiene como objetivo el diseño y construcción de un dataset robusto de rostros para el entrenamiento y validación de modelos de reconocimiento facial. La particularidad de este conjunto de datos radica en su naturaleza híbrida, combinando imágenes de un entorno controlado (alumnos y familiares) con imágenes de entornos silvestres (in-the-wild) provenientes de figuras públicas y datasets preexistentes.


2. Composición del Dataset
Para garantizar la capacidad de generalización del modelo, el dataset contendrá al menos 10 categorías distintas:

Alumnos: Imágenes recolectadas de los integrantes del equipo bajo diferentes condiciones de iluminación y ángulos.

En caso de trabajar individualmente, incluir a familiares

Famosos: Integración de muestras de datasets estándar (ej. VGGFace2, LFW - Labeled Faces in the Wild o CelebA) para proporcionar una base de comparación contra estándares internacionales.

Estructura:
\Dataset
  \Alumno1
  \Alumno2
  \Alumno3
  \Famoso1
  \Famoso2
  \Famoso3
  ...

3. Metodología de Adquisición
El proceso de creación seguirá las siguientes fases técnicas:

Captura y Curación: Toma de fotografías asegurando diversidad en la expresión facial y el uso de accesorios (lentes, mascarillas).

Preprocesamiento: Aplicación de algoritmos de detección (como MTCNN o Haar Cascades) para el recorte (cropping) y alineación de los rostros a una resolución uniforme (ej. 160 x 160 píxeles).

Aumentación de Datos: Uso de técnicas de rotación, cambio de brillo y espejo para multiplicar la efectividad del entrenamiento sin necesidad de nuevas capturas.
