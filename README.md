# Foosball Match Simulator

Dette repoet inneholder en liten objektorientert Kotlin-applikasjon som simulerer foosball-matcher.

Repoet brukes i et UML- og modelleringskurs for SI og NK.

Målet er å gi SI og NK en lesbar, objektorientert Kotlin-kodebase som kan brukes til å lære om modellering og verktøy som brukes for modellering.

---

## Kursoppsett

Under kurset får man:
- Praktisk erfaring av verktøy som brukes til modellering av systemer
- Bruk av modelleringsverktøy for å sette seg inn i eksisterende systemer
- Bruk av modelleringsverktøy for å utvide systemer med ny funksjonalitet.
- Bruk av modelleringsverktøy som dokumentasjon

---

## Forutsetninger

Kurset kan gjennomføres uten å kjøre koden, men krever nedlasting av visse verktøy.

---

### Nice to have

Last ned disse om du ønsker å se og jobbe med koden i editor.

1. **VS Code**  
Brukes som hovedverktøy for å lese, navigere og jobbe med kodebasen.  
https://code.visualstudio.com/

2. **Git**  
Brukes for å klone repoet.
https://git-scm.com/

3. **VS Code Extensions**

| Extension | Hvorfor? |
|---|---|
| **Kotlin Language** | Syntaksutheving og navigasjon i Kotlin-kode |
| **Mermaid Markdown Syntax Highlighting** | Gjenkjenner Mermaid-kode i markdown-filer |
| **Markdown Preview Mermaid Support** | Lar deg forhåndsvise Mermaid-diagrammer i VS Code |

---

### Nødvendinge verktøy (trenger ikke installasjon)

Disse brukes til modellering, visualisering og samarbeid direkte i nettleser:

- **Miro** – fri modellering og samarbeid i grupper  
  https://miro.com/  
  Egner seg godt til idémyldring, systemskisser og workshop-arbeid.

- **draw.io (diagrams.net)** – strukturert UML og systemdiagrammer  
  https://app.diagrams.net/  
  Egner seg spesielt godt til UML-diagrammer, arkitektur og mer presise systemmodeller.

- **Mermaid Live Editor** – rask visualisering av Mermaid-diagrammer fra kode  
  https://mermaid.live  
  Brukes til å teste og validere Mermaid-diagrammer uten oppsett i IDE.

---

### Valgfritt (for å kjøre prosjektet lokalt)

Disse verktøyene er kun nødvendige dersom du ønsker å kjøre koden og jobbe mer praktisk med prosjektet.

1. **Visual Studio Code**  
Last ned her: https://code.visualstudio.com/  
Brukes til å lese, kjøre kode og forhåndsvise Mermaid-diagrammer.

2. **Java JDK (17 eller nyere, anbefalt 21)**  
Last ned her: https://adoptium.net/  
Kotlin kjører på JVM og krever derfor Java installert.

3. **Kotlin**  
Trengs ikke å installeres separat, da det følger med via Gradle i prosjektet.

4. **Gradle**  
Prosjektet bruker Gradle Wrapper (`./gradlew`), så det er ikke nødvendig å installere Gradle globalt.  
Brukes til å bygge og kjøre prosjektet.

5. **Terminal / kommandolinje**  
Brukes til å kjøre Gradle-kommandoer og starte prosjektet:
```bash
./gradlew run
```
---