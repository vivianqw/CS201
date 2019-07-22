
import java.util.*; 

/**
 *	Interface that all compression suites must implement. That is they must be
 *	able to compress a file and also reverse/decompress that process.
 * 
 *	@author Brian Lavallee
 *	@since 5 November 2015
 *  @author Owen Atrachan
 *  @since December 1, 2016
 */
public class HuffProcessor {

	public static final int BITS_PER_WORD = 8;
	public static final int BITS_PER_INT = 32;
	public static final int ALPH_SIZE = (1 << BITS_PER_WORD); // or 256
	public static final int PSEUDO_EOF = ALPH_SIZE;
	public static final int HUFF_NUMBER = 0xface8200;
	public static final int HUFF_TREE  = HUFF_NUMBER | 1;
	public static final int HUFF_COUNTS = HUFF_NUMBER | 2;

	public enum Header{TREE_HEADER, COUNT_HEADER};
	public Header myHeader = Header.TREE_HEADER;

	/**
	 * Compresses a file. Process must be reversible and loss-less.
	 *
	 * @param in
	 *            Buffered bit stream of the file to be compressed.
	 * @param out
	 *            Buffered bit stream writing to the output file.
	 */
	public void compress(BitInputStream in, BitOutputStream out){
		int[] counts = readForCounts(in);
		HuffNode root = makeTreeFromCounts(counts);
		String[] codings = makeCodingsFromTree(root);
		writeHeader(root, out); 
		in.reset();
		writeCompressedBits(in, codings, out);
	}
	public int[] readForCounts(BitInputStream in) {
		int[] output = new int[ALPH_SIZE];
		while(true){
			int character = in.readBits(BITS_PER_WORD);
			if(character == -1) {
				break;
			}
			output[character]++;
		} 
		return output; 
	}

	public HuffNode makeTreeFromCounts(int[] in) {
		PriorityQueue<HuffNode> pq = new PriorityQueue<HuffNode>();
		// call pq.add(new HuffNode(...)) for every 8-bit
		// value that occurs one or more times, including PSEUDO_EOF!!
		// these values/counts are in the array of counts

		for (int i=0; i<ALPH_SIZE; i++) {
			if (in[i] != 0) {
				HuffNode hn = new HuffNode(i, in[i]); 
				pq.add(hn); 
			}
		}
		HuffNode pseudo = new HuffNode(PSEUDO_EOF, 1); 
		pq.add(pseudo); 
		while (pq.size() > 1) {
			HuffNode left = pq.remove();
			HuffNode right = pq.remove();
			HuffNode t = new HuffNode(-1,left.weight() + right.weight(),left,right);
			pq.add(t);
		}
		HuffNode root = pq.remove();
		return root; 
	}
	public String[] makeCodingsFromTree(HuffNode hn) {
		String[] codes = new String[ALPH_SIZE + 1]; 
		this.makeCodingsHelper(hn, "", codes);
		return codes;
	}
	public void makeCodingsHelper(HuffNode hn, String path, String[] follow) {
		if (hn.left() == null && hn.right() == null) {
			int current = hn.value(); 
			follow[current] = path; 
			return; 
		}
		makeCodingsHelper(hn.left(), path + 0, follow); 
		makeCodingsHelper(hn.right(), path + 1, follow);		
	}
	public void writeHeader(HuffNode hn, BitOutputStream out) { 
		out.writeBits(BITS_PER_INT, HUFF_TREE);
		writeTree(hn, out); 
	}
	public void writeTree(HuffNode hn, BitOutputStream out) {
		if (hn.left() == null && hn.right() == null) {
			out.writeBits(1, 1);
			out.writeBits(BITS_PER_WORD + 1, hn.value());
			return; 
		}	
		out.writeBits(1, 0);
		writeTree(hn.left(), out); 
		writeTree(hn.right(), out); 
	}
	public void writeCompressedBits(BitInputStream in, String[] code, BitOutputStream out) {
		while(true){
			int character = in.readBits(BITS_PER_WORD);
			if(character == -1) {
				break;
			}
			String current = code[character];
			out.writeBits(current.length(), Integer.parseInt(current, 2));
		}
		String pseudo = code[PSEUDO_EOF];
		out.writeBits(pseudo.length(), Integer.parseInt(pseudo, 2));
	}
	/**
	 * Decompresses a file. Output file must be identical bit-by-bit to the
	 * original.
	 *
	 * @param in
	 *            Buffered bit stream of the file to be decompressed.
	 * @param out
	 *            Buffered bit stream writing to the output file.
	 */
	public void decompress(BitInputStream in, BitOutputStream out){
		int id = in.readBits(BITS_PER_INT); 
		if (id != HUFF_TREE) {
			throw new HuffException("Not the magic number!"); 
		}
		//check id to see if valid compressed file

		HuffNode root = readTreeHeader(in); 
		readCompressedBits(root, in, out); 
	}
	public HuffNode readTreeHeader(BitInputStream in) {
		//int magic = HUFF_TREE; 
		int currentBit = in.readBits(1); 
		if (currentBit == 1) {
			return new HuffNode(in.readBits(BITS_PER_WORD+1), 1, null, null); 
		}
		if (currentBit == 0) {
			HuffNode left = readTreeHeader(in); 
			HuffNode right = readTreeHeader(in); 
			return new HuffNode(-1, 1, left, right); 
		}
		return new HuffNode(-1,1); 
	}

	public void readCompressedBits(HuffNode root, BitInputStream in, BitOutputStream out) {
		HuffNode current = root; 
		while (true) {
			int bits = in.readBits(1); 
			if (bits == -1) {
				throw new HuffException("File ended before PSEUDO_EOF"); 
			}
			if (bits == 0) {
				current = current.left(); 
			}
			if (bits == 1) {
				current = current.right(); 
			}
			if (current.left() == null && current.right() == null) {
				if (current.value() == PSEUDO_EOF) {
					return; 
				}
				else {
					out.writeBits(BITS_PER_WORD, current.value());
					current = root; 
				}
			}
		}
	}

	public void setHeader(Header header) {
		myHeader = header;
		System.out.println("header set to "+myHeader);
	}
}