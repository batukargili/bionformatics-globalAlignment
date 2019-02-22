# bionformatics-globalAlignment
Java code that finds pairwise sequence alignments for all possible pairs of sequences and construct similarity matrix.

- Finds pairwise sequence alignments for all possible pairs of sequences.
- Global alignment to find each pairwise sequence alignment constructed
- Match and mismatch scores for amino acids are taken from the BLOSUM62
  matrix.
  
- Similarity Score (s i , s j ) = # of exact matches / aligned sequence length

- Constructs similarity matrix (kxk) by using similarity scores.
- Builds guide tree with using similarity matrix.
